package com.mahmoud.android.cinematica.ui.movieDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.mahmoud.android.cinematica.R
import androidx.navigation.fragment.findNavController

import com.mahmoud.android.cinematica.databinding.FragmentMovieDetailsBinding
import com.mahmoud.android.cinematica.ui.base.BaseFragment
import com.mahmoud.android.cinematica.utilities.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding>() {
    override val layoutIdFragment = R.layout.fragment_movie_details
    override val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()
    private lateinit var detailAdapter: DetailAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setTitle(false)
        setDetailAdapter()
        observeEvents()
    }

    private fun setDetailAdapter(){
        detailAdapter = DetailAdapter(emptyList(), viewModel)
        binding.movieDetailsRecycler.adapter = detailAdapter
    }

    private fun observeEvents(){
        viewModel.clickMovieEvent.observeEvent(viewLifecycleOwner) {
            viewModelStore.clear()
            navigateToMovie(it)
        }

        viewModel.clickBackEvent.observeEvent(viewLifecycleOwner) { goBack() }
    }

    private fun navigateToMovie(movieId: Int) {
        val action = MovieDetailsFragmentDirections.actionMovieDetailsFragment(movieId)
        findNavController().navigate(action)
    }

    private fun goBack() {
        findNavController().navigateUp()
    }
}