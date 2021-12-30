package com.assignment.views.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.assignment.utils.ResourceState
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import assignment.app.R
import com.assignment.views.adapter.UserDetailAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

/**
 * A simple [Fragment] subclass.
 */
@AndroidEntryPoint
class HomeFragment : Fragment(), HomeNavigator {
    private val homeViewModel by viewModels<HomeViewModel>()
    lateinit var userDetailAdapter: UserDetailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        homeViewModel.setNavigator(this)
        initObservers()
    }

    private fun initObservers() {
        homeViewModel.userDetailsLiveData.observe(this, Observer {
            when (it?.status) {
                ResourceState.LOADING -> {
                    shimmer_view_container.visibility = View.VISIBLE
                    shimmer_view_container.startShimmer()
                }
                ResourceState.SUCCESS -> {
                    it.data?.let {
                        shimmer_view_container.visibility = View.GONE
                        shimmer_view_container.stopShimmer()

                        val layoutManager = LinearLayoutManager(context)
                        rv_user!!.layoutManager = layoutManager
                        OverScrollDecoratorHelper.setUpOverScroll(rv_user, OverScrollDecoratorHelper.ORIENTATION_VERTICAL)
                        if (rv_user.itemDecorationCount == 0){
                            val divider = DividerItemDecoration(rv_user.context, DividerItemDecoration.VERTICAL)
                            divider.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.row_divider)!!)
                            rv_user.addItemDecoration(divider)
                        }
                        userDetailAdapter = UserDetailAdapter(requireContext(),userList = it)
                        rv_user!!.adapter = userDetailAdapter
                    }
                }
                ResourceState.ERROR -> {
                }
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.getUsers()
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
