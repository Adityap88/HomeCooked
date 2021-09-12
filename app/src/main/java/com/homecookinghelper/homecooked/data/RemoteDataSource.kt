package com.homecookinghelper.homecooked.data

import com.homecookinghelper.homecooked.data.network.FoodRecipesAPI
import com.homecookinghelper.homecooked.models.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val  foodRecipesAPI: FoodRecipesAPI
){
    suspend fun getRecipes(queries:Map<String,String>):Response<FoodRecipe>{
        return foodRecipesAPI.getRecipes(queries)
    }
}