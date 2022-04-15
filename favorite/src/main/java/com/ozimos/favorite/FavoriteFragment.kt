package com.ozimos.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.ozimos.core.domain.models.MovieDomain
import com.ozimos.di.favoriteModule
import com.ozimos.favorite.databinding.FragmentFavoriteBinding
import com.ozimos.made.detail.DetailActivity
import com.ozimos.made.home.MovieAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.GlobalContext.loadKoinModules


class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!
    private val viewModel: FavoriteViewModel by viewModel()
    private val adapter by lazy { MovieAdapter(listMovie) }
    private val listMovie = ArrayList<MovieDomain>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)

        loadKoinModules(favoriteModule)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null){
            setObserver()
            setView()
        }
    }

    private fun setView() {
        binding.run {
            adapter.onClick(object : MovieAdapter.OnClickItem {
                override fun onClickItem(data: Any?, position: Int) {
                    if (data is MovieDomain) {
                        startActivity(
                            Intent(requireActivity(), DetailActivity::class.java)
                                .putExtra("detail", data)
                        )
                    }
                }
            })
            rvMovie.adapter = adapter
            rvMovie.setHasFixedSize(true)
        }
    }

    private fun setObserver() {
        listMovieObserver()
    }

    private fun listMovieObserver() {
        viewModel.list.observe(viewLifecycleOwner) {

            setData(it)
        }
    }

    private fun setData(data: List<MovieDomain>?) {
        binding.rvMovie.isVisible = !data.isNullOrEmpty()
        binding.layoutEmpty.isVisible = data.isNullOrEmpty()

        val tempSize = listMovie.size
        listMovie.clear()
        adapter.notifyItemRangeRemoved(0, tempSize)
        listMovie.addAll(data ?: emptyList())
        adapter.notifyItemRangeInserted(0, listMovie.size)

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}