package com.homecookinghelper.homecooked.viewmodels

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.*
import com.homecookinghelper.homecooked.data.Repository
import com.homecookinghelper.homecooked.models.FoodRecipe
import com.homecookinghelper.homecooked.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response

import java.lang.Exception

import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
    application: Application
) : AndroidViewModel(application),LifecycleObserver {

     val recipesResponse:MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    fun getRecipes(queries: Map<String,String>)= viewModelScope.launch {
        getRecipesSafeCall(queries)
    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {

        recipesResponse.value=NetworkResult.Loading()
        if(hasInternetConnection()){
            try {
                val response= repository.remote.getRecipes(queries)
                recipesResponse.value=handleResponse(response)
            }catch (e:Exception){

            }
        }else{
            recipesResponse.value=NetworkResult.Error("No Internet Connection")
        }
    }

    private fun handleResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe>? {
            when {
                response.message().toString().contains("timeout")->{
                    return NetworkResult.Error("Network Error")
                }
                response.code()==402->{
                    return NetworkResult.Error("API key Limited")
                }
                response.body()!!.results.isNullOrEmpty()->{
                    return NetworkResult.Error("Recipes not found")
                }
                response.isSuccessful->{
                    val foodRecipes=response.body()
                    return NetworkResult.Success(foodRecipes!!)
                }
                else ->{
                    return NetworkResult.Error(response.message())
                }
            }
    }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<Application>().getSystemService(
            Context.CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false

        }
    }
}