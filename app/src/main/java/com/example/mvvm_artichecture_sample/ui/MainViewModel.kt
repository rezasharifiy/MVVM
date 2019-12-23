package com.example.mvvm_artichecture_sample.ui

import android.app.Application

import androidx.lifecycle.MutableLiveData

import com.example.mvvm_artichecture_sample.R
import com.example.mvvm_artichecture_sample.base.BaseViewModel
import com.example.mvvm_artichecture_sample.base.model.MessageModel
import com.example.mvvm_artichecture_sample.base.network.baseobserver.ErrorConsumer
import com.example.mvvm_artichecture_sample.base.network.baseobserver.SuccessConsumer
import com.example.mvvm_artichecture_sample.base.network.baseobserver.WebServiceListener
import com.example.mvvm_artichecture_sample.base.network.model.APIError
import com.example.mvvm_artichecture_sample.data.MainRepository
import com.example.mvvm_artichecture_sample.data.remote.apimodel.CountryModel
import com.example.mvvm_artichecture_sample.data.remote.apimodel.ReposnseModel

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(repository: MainRepository, application: Application) : BaseViewModel<MainRepository>(application, repository), WebServiceListener {


    private val showLoadig = MutableLiveData<Boolean>()
    private val showMessage = MutableLiveData<MessageModel>()
    private val countryList = MutableLiveData<List<CountryModel>>()
    private val showToast = MutableLiveData<String>()
    private var list: List<CountryModel>? = null


    init {
        fetchList()
    }

    fun fetchList() {
        if (isNetworkConnected) {
            showMessage(false, "", "")
            showLoading(true)
            addDisposable(repository!!.list
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(SuccessConsumer(this, MAIN_REQUEST), ErrorConsumer(this, MAIN_REQUEST)))
        } else {
            showMessage(true, getString(R.string.error), getString(R.string.internet_error))
        }


    }

    private fun setList(list: List<CountryModel>?) {
        countryList.setValue(list)
    }

    fun updateList(): MutableLiveData<List<CountryModel>> {
        return countryList
    }

    private fun showMessage(show: Boolean, title: String, message: String) {
        showMessage.setValue(MessageModel(show, title, message))
    }

    fun message(): MutableLiveData<MessageModel> {
        return showMessage
    }

    private fun showLoading(show: Boolean) {
        showLoadig.setValue(show)
    }

    fun loading(): MutableLiveData<Boolean> {
        return showLoadig
    }

    @Throws(Exception::class)
    override fun onSuccess(`object`: Any, statusCode: Int, requestType: String) {
        showLoading(false)
        list = (`object` as ReposnseModel).countryModel
        setList(list)
    }

    fun showToast(): MutableLiveData<String> {
        return showToast
    }

    @Throws(Exception::class)
    override fun onError(apiError: APIError, statusCode: Int, requestType: String) {
        showLoading(false)
        showMessage(true,getString(R.string.error),apiError.message?:getString(R.string.errorRecieveData))
    }

    fun itemClicked(position: Int) {
        if (list != null) {
            showToast.setValue(list!![position].code.toString())
        }
    }

    companion object {
        private val MAIN_REQUEST = "main_list"
    }
}
