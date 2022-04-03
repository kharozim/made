package com.ozimos.made.home

import android.content.Intent
import android.os.Bundle
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

    private lateinit var binding: FragmentHomeBinding
    private val viewmodel: HomeViewModel by viewModel()
    private val adapter by lazy { MovieAdapter(filtered) }
    private val listMovie = ArrayList<MovieDomain>()
    private val filtered = ArrayList<MovieDomain>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        setObserver()
        setView()


        return binding.root
    }

    private fun doFilter(keyWord: String) {
        filtered.clear()
        val data = listMovie.filter { it.name.contains(keyWord, true) }
        filtered.addAll(data)
        adapter.notifyDataSetChanged()


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
                    showSnackBar(binding.root, it.msg ?: "error")
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


}