package com.homecookinghelper.homecooked.ui.fragments.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.homecookinghelper.homecooked.databinding.FragmentRecipeBinding


class RecipeFrag : Fragment() {

    public var _binding:FragmentRecipeBinding?=null

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentRecipeBinding.inflate(inflater, container, false)
        val view= _binding!!.root
        _binding!!.recyclerView.showShimmer()
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }




}