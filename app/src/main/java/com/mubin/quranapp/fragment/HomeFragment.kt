package com.mubin.quranapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.mubin.quranapp.R
import com.mubin.quranapp.adapter.MainItemAdapter
import com.mubin.quranapp.databinding.FragmentHomeBinding
import com.mubin.quranapp.model.Result
import com.mubin.quranapp.response.QuranResponse
import com.mubin.quranapp.viewModel.QuranViewModel
import java.util.*

class HomeFragment : Fragment() {

    private var fragmentHomeBinding: FragmentHomeBinding? = null
    private var adapter: MainItemAdapter? = null
    private val results: MutableList<Result> = ArrayList()
    private var currentPage = 0
    private var totalAvailablePages = 1
    private var quranViewModel: QuranViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val view = fragmentHomeBinding!!.getRoot()
        quranViewModel = ViewModelProvider(this).get(QuranViewModel::class.java)
        adapter = MainItemAdapter(requireContext(), results)
        fragmentHomeBinding!!.arbiRV.adapter = adapter
        fragmentHomeBinding!!.arbiRV.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!fragmentHomeBinding!!.arbiRV.canScrollVertically(1)) {
                    if (currentPage <= totalAvailablePages) {
                        currentPage += 1
                        getResults()
                    }
                }
            }
        })
        getResults()
        return view
    }

    private fun getResults() {
        toggleLoading()
        quranViewModel!!.getQuran("quran", currentPage)
            .observe(viewLifecycleOwner, { quranResponse: QuranResponse? ->
                toggleLoading()
                if (quranResponse != null) {
                    totalAvailablePages = quranResponse.totalPages
                    if (quranResponse.results != null) {
                        val OldCount = results.size
                        results.addAll(quranResponse.results)
                        adapter!!.notifyItemRangeInserted(OldCount, results.size)
                    }
                }
            })
    }

    private fun toggleLoading() {
        if (currentPage == 0) {
            if (fragmentHomeBinding!!.isLoading != null && fragmentHomeBinding!!.isLoading == true) {
                fragmentHomeBinding!!.isLoading = false
            } else {
                fragmentHomeBinding!!.isLoading = true
            }
        } else {
            if (fragmentHomeBinding!!.isLoadingMore != null && fragmentHomeBinding!!.isLoadingMore == true) {
                fragmentHomeBinding!!.isLoadingMore = false
            } else {
                fragmentHomeBinding!!.isLoadingMore = true
            }
        }
    }
}