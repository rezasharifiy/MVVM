package com.example.mvvm_artichecture_sample.ui

import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.mvvm_artichecture_sample.R
import com.example.mvvm_artichecture_sample.base.BaseFragment
import com.example.mvvm_artichecture_sample.base.ViewModelFactory
import com.example.mvvm_artichecture_sample.data.remote.apimodel.CountryModel
import com.example.mvvm_artichecture_sample.ui.adpater.MainAdapter
import com.example.mvvm_artichecture_sample.ui.adpater.MainAdapterHanlder

class MainFragment : BaseFragment<MainViewModel>(), MainAdapterHanlder {
    private var mRecyclerView: RecyclerView? = null
    private var mainPb: ProgressBar? = null
    private var errorTv: TextView? = null
    private var mainAdapter: MainAdapter? = null

    override val viewModel: MainViewModel
        get() = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)

    private val factory: ViewModelProvider.Factory get() = ViewModelFactory(this)

    override val layoutId: Int
        get() = R.layout.fragment_main

    override fun setupView() {
        findView()
        setAdapter()
        updateList()
        observeLoading()
        observeShowMessage()
        observeShowErrorMessage()
    }

    private fun updateList() {
        mViewModel!!.updateList().observe(this, Observer { list -> addList(list) })
    }

    private fun addList(list: List<CountryModel>) {
        mainAdapter!!.addItem(list)
    }


    private fun observeShowErrorMessage() {
        mViewModel!!.message().observe(this, Observer { messageModel -> showErrorMessage(messageModel.isShow, messageModel.message!!) })
    }

    private fun observeShowMessage() {
        mViewModel!!.showToast().observe(this, Observer { message -> showToast(message) })
    }

    private fun observeLoading() {
        mViewModel!!.loading().observe(this, Observer { show -> showProgressBar(show!!) })
    }

    private fun setAdapter() {
        mainAdapter = MainAdapter(activityContext!!, this)
        mRecyclerView!!.layoutManager = LinearLayoutManager(activityContext)
        mRecyclerView!!.adapter = mainAdapter
    }

    private fun findView() {
        mRecyclerView = currView!!.findViewById(R.id.main_rv)
        mainPb = currView!!.findViewById(R.id.main_pb)
        errorTv = currView!!.findViewById(R.id.main_error_tv)
    }

    private fun showErrorMessage(show: Boolean, message: String) {
        errorTv!!.visibility = getVisibility(show)
        errorTv!!.text = message
    }

    private fun showProgressBar(show: Boolean) {
        mainPb!!.visibility = getVisibility(show)
    }

    private fun getVisibility(show: Boolean): Int {
        return if (show) View.VISIBLE else View.GONE
    }

    private fun showToast(message: String) {
        Toast.makeText(activityContext, message, Toast.LENGTH_SHORT).show()
    }

    override fun onItemClick(position: Int) {
        mViewModel!!.itemClicked(position)

    }

    companion object {

        val TAG = MainFragment::class.java!!.getName()

        val instance: MainFragment
            get() = MainFragment()
    }
}
