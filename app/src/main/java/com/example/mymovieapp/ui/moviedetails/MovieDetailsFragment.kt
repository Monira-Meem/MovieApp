package com.example.mymovieapp.ui.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mymovieapp.databinding.FragmentMovieDetailsBinding
import com.example.mymovieapp.utils.LoaderDialog

class MovieDetailsFragment : Fragment() {

    private lateinit var binding : FragmentMovieDetailsBinding
    private val args : MovieDetailsFragmentArgs by navArgs()
    private val viewModel : MovieDetailsViewModel by viewModels()
    private val loaderDialog by lazy { LoaderDialog(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpObserver()
        viewModel.getMovieDetails(args.movie.id!!)

    }

    private fun setUpObserver() {

        viewModel.movieDetails.observe(viewLifecycleOwner) {
            loaderDialog.showLoader()
            if (it.data != null) {
                loaderDialog.hideLoader()
                binding.rootLayout.visibility = View.VISIBLE
                binding.tvMovieTitle.text = it.data.title
                binding.tvMovieTagline.text = it.data.tagline
                binding.movieReleaseDate.text = it.data.releaseDate
                binding.ivMovieInfo.text = it.data.overview
                Glide.with(binding.root)
                    .load("https://image.tmdb.org/t/p/w500"+it.data.posterPath)
                    .into(binding.ivMoviePoster)
            }
        }


        viewModel.showLoader.observe(viewLifecycleOwner) {
            if (it)
                loaderDialog.showLoader()
            else
                loaderDialog.hideLoader()
        }

        viewModel.toastMessage.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(),it, Toast.LENGTH_LONG).show()
        }

    }

}