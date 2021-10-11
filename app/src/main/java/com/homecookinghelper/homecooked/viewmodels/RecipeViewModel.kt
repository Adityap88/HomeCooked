package com.homecookinghelper.homecooked.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.homecookinghelper.homecooked.util.Constants
import com.homecookinghelper.homecooked.util.Constants.Companion.QUERY_ADD_RECIPE_INFO
import com.homecookinghelper.homecooked.util.Constants.Companion.QUERY_APIKEY
import com.homecookinghelper.homecooked.util.Constants.Companion.QUERY_DIET
import com.homecookinghelper.homecooked.util.Constants.Companion.QUERY_FILL_INGREDIENTS
import com.homecookinghelper.homecooked.util.Constants.Companion.QUERY_NUMBER
import com.homecookinghelper.homecooked.util.Constants.Companion.QUERY_TYPE

class RecipeViewModel(application: Application):AndroidViewModel(application) {

     fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()
        queries[QUERY_NUMBER] = "50"
        queries[QUERY_APIKEY] = Constants.API_KEY
        queries[QUERY_TYPE] = "snack"
        queries[QUERY_DIET] = "vegan"
        queries[QUERY_ADD_RECIPE_INFO] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"
        return queries

    }
}