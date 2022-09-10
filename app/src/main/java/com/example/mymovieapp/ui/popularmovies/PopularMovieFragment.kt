package com.example.mymovieapp.ui.popularmovies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mymovieapp.data.model.Movie
import com.example.mymovieapp.databinding.FragmentPopularMovieBinding
import com.example.mymovieapp.ui.adapter.MovieAdapter
import com.example.mymovieapp.ui.adapter.OnItemClickListener
import com.example.mymovieapp.utils.Constants.Companion.QUERY_PAGE_SIZE
import com.example.mymovieapp.utils.LoaderDialog
import com.example.mymovieapp.utils.SpacesItemDecoration

class PopularMovieFragment : Fragment() {

    private lateinit var binding : FragmentPopularMovieBinding
    private var movieAdapter : MovieAdapter? = null
    private val viewModel : PopularMovieViewModel by viewModels()
    private val loaderDialog by lazy { LoaderDialog(requireContext()) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularMovieBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
        initRecyclerView()

        viewModel.getPopularMovie()
    }

    private fun setUpObserver() {

        viewModel.movieList.observe(viewLifecycleOwner) {

            if (it.isNotEmpty()) {
//                binding.noDataLinearLayout.visibility = View.GONE
                movieAdapter?.setData(it)
            } else {
 //               binding.noDataLinearLayout.visibility = View.VISIBLE
            }
        }

        /*
        viewModel.movieLiveData.observe(viewLifecycleOwner) {

            if (it.movieList.isNotEmpty()) {
                movieAdapter?.setData(it.movieList)

                val totalPages = it.totalResults / QUERY_PAGE_SIZE + 2
                isLastPage = viewModel.popularMoviesPage == totalPages

                if(isLastPage) {
                    binding.recyclerView.setPadding(0,0,0,0)
                }

            } else {
                binding.recyclerView.visibility = View.GONE
                binding.noDataLinearLayout.visibility = View.VISIBLE
            }
        }

         */

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

    private fun initRecyclerView() {
        movieAdapter = MovieAdapter().apply {

            setOnItemClickListener(object : OnItemClickListener {

                override fun onItemClick(movie: Movie) {
                    val action = PopularMovieFragmentDirections
                        .actionPopularMovieFragmentToMovieDetailsFragment(movie)
                    findNavController().navigate(action)
                }
            })
        }

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(),3)
            adapter = movieAdapter
            addItemDecoration(SpacesItemDecoration(5))
            addOnScrollListener(this@PopularMovieFragment.scrollListener)
        }
    }


    var isLoading = false
    var isLastPage = false
    var isScrolling = false

    private val scrollListener = object : RecyclerView.OnScrollListener() {

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val layoutManager = recyclerView.layoutManager as GridLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning
                    && isTotalMoreThanVisible && isScrolling

            if (shouldPaginate) {
                viewModel.getPopularMovie()
                isScrolling = false
            }
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)

            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }
    }


}