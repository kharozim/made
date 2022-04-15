package com.ozimos.made.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.ozimos.core.data.Resourse
import com.ozimos.core.domain.models.MovieDomain
import com.ozimos.made.databinding.FragmentHomeBinding
import com.ozimos.made.detail.DetailActivity
import com.ozimos.made.utils.showSnackBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: HomeViewModel by viewModel()
    private val filtered = ArrayList<MovieDomain>()
    private val listMovie = ArrayList<MovieDomain>()
    private val adapter = MovieAdapter(filtered)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            setObserver()
            setView()
        }
    }

    private fun doFilter(keyWord: String) {
        val tempSize = filtered.size
        filtered.clear()
        adapter.notifyItemRangeRemoved(0, tempSize)
        val data = listMovie.filter { it.name.contains(keyWord, true) }
        filtered.addAll(data)
        adapter.notifyItemRangeInserted(0, filtered.size)


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
            edtFilter.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(text: String?): Boolean {
                    doFilter(text ?: "")
                    return true
                }

            })

        }
    }

    private fun setObserver() {
        listMovieObserver()
    }

    private fun listMovieObserver() {
        viewmodel.list.observe(viewLifecycleOwner) {
            when (it) {
                is Resourse.Loading -> {}
                is Resourse.Success -> {
                    setData(it.data)
                }
                is Resourse.Error -> {
                    binding.root.let { it1 -> showSnackBar(it1, it.msg ?: "error") }
                }
            }
        }
    }

    private fun setData(data: List<MovieDomain>?) {
        binding.rvMovie.isVisible = !data.isNullOrEmpty()
        binding.layoutEmpty.isVisible = data.isNullOrEmpty()

        listMovie.clear()
        listMovie.addAll(data ?: emptyList())
        doFilter("")
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}