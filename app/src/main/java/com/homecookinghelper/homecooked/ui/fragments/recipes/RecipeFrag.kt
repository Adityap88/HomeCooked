package com.homecookinghelper.homecooked.ui.fragments.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.homecookinghelper.homecooked.viewmodels.MainViewModel
import com.homecookinghelper.homecooked.R
import com.homecookinghelper.homecooked.adapters.RecipesAdapter
import com.homecookinghelper.homecooked.util.Constants.Companion.API_KEY
import com.homecookinghelper.homecooked.util.NetworkResult
import com.homecookinghelper.homecooked.viewmodels.RecipeViewModel
import com.todkars.shimmer.ShimmerRecyclerView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeFrag : Fragment() {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipeViewModel: RecipeViewModel
    private val mAdapter by lazy { RecipesAdapter() }
    private lateinit var mView: View
    private lateinit var recycler: ShimmerRecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipeViewModel=ViewModelProvider(requireActivity()).get(RecipeViewModel::class.java)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_recipe, container, false)
        recycler = mView.findViewById(R.id.recycler_view)

        setUpRecyclerView()
        requestData()
        return mView
    }

    private fun requestData() {
        mainViewModel.getRecipes(recipeViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner, { response ->
            when (response) {
                is NetworkResult.Success -> {
                    hideShimmerEffect()
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    hideShimmerEffect()
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect()
                }
            }
        })
    }



    private fun setUpRecyclerView() {
        recycler.adapter = mAdapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        recycler.showShimmer()
    }

    private fun hideShimmerEffect() {
        recycler.hideShimmer()
    }

}