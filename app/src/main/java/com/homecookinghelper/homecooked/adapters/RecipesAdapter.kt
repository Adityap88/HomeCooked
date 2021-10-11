package com.homecookinghelper.homecooked.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.homecookinghelper.homecooked.databinding.RecipeRowLayoutBinding
import com.homecookinghelper.homecooked.models.FoodRecipe
import com.homecookinghelper.homecooked.models.Result
import com.homecookinghelper.homecooked.util.RecipesDiffUtil

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.MyViewHolder>() {

    private var recipe = emptyList<Result>()

    class MyViewHolder(private val binding: RecipeRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(result: Result) {
            binding.result = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RecipeRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentResult = recipe[position]
        holder.bind(currentResult)

    }

    override fun getItemCount(): Int {
        return recipe.size
    }
    fun setData(newData:FoodRecipe){
        val recipeDiffUitl=RecipesDiffUtil(recipe,newData.results)
        val diffUtilResult= DiffUtil.calculateDiff(recipeDiffUitl)
        recipe=newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }
}