package com.homecookinghelper.homecooked.data.network

import com.homecookinghelper.homecooked.models.FoodRecipe
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface FoodRecipesAPI {
    @GET("/recipes/complexSearch")
    suspend fun getRecipes(@QueryMap queryMap: Map<String, String>): Response<FoodRecipe>

}