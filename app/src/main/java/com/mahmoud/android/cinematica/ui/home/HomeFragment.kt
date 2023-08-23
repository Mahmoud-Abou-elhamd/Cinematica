package com.mahmoud.android.cinematica.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mahmoud.android.cinematica.R
import com.mahmoud.android.cinematica.databinding.FragmentHomeBinding
import com.mahmoud.android.cinematica.ui.base.BaseFragment
import com.mahmoud.android.cinematica.utilities.EventObserve
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override val layoutIdFragment = R.layout.fragment_home
    override val viewModel: HomeViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMovieAdapter()
        observeEvents()
    }

    private fun setMovieAdapter(){
        binding.moviesRecycler.adapter =
            MovieAdapter(mutableListOf(), R.layout.item_movie, viewModel)
    }

    private fun observeEvents() {
        viewModel.clickMovieEvent.observe(viewLifecycleOwner, EventObserve { movieId ->
            navigateToMovieDetails(movieId)
        })
    }

    private fun navigateToMovieDetails(movieId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToMovieDetailsFragment(movieId)
        findNavController().navigate(action)
    }
}