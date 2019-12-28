package com.example.mvvm_artichecture_sample.ui

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_artichecture_sample.R
import com.example.mvvm_artichecture_sample.base.BaseViewModel
import com.example.mvvm_artichecture_sample.base.model.MessageModel
import com.example.mvvm_artichecture_sample.base.network.Output
import com.example.mvvm_artichecture_sample.data.MainRepository
import com.example.mvvm_artichecture_sample.data.remote.apimodel.Country
import com.example.mvvm_artichecture_sample.data.remote.apimodel.ResponsModel
import kotlinx.coroutines.launch

class MainViewModel(repository: MainRepository, application: Application) : BaseViewModel<MainRepository>(application, repository) {

    private val showLoading = MutableLiveData<Boolean>()
    private val showMessage = MutableLiveData<MessageModel>()
    private val countryList = MutableLiveData<List<Country>>()
    private val showToast = MutableLiveData<String>()
    private var list: List<Country>? = null
    private var output: Output<ResponsModel>? = null

    init {
        fetchList()
    }

    private fun fetchList() {
        if (isNetworkConnected) {

            showMessage(false, "", "")

            scope.launch {

                showLoading(true)

                callListApi()

                manageResponse(output!!)

                showLoading(false)
            }

        } else {

            showMessage(true, getString(R.string.error), getString(R.string.internet_error))

        }
    }

    private fun manageResponse(output: Output<ResponsModel>) {


        when (output) {

            is Output.Success -> {
                manageSuccessResponse(output)
            }

            is Output.Error -> {
                manageErrorResponse(output)
            }

        }

    }

    private suspend fun callListApi() {
        output = repository!!.countries(MAIN_REQUEST)
    }

    private fun manageErrorResponse(output: Output.Error) {
        showMessage(true, getString(R.string.error), output.apiError.message!!)
    }

    private fun manageSuccessResponse(output: Output.Success<ResponsModel>) {
        list = output.response.result
        setList(list!!)
    }

    private fun setList(list: List<Country>) {

        countryList.postValue(list)
    }

    fun updateList(): MutableLiveData<List<Country>> {
        return countryList
    }

    private fun showMessage(show: Boolean, title: String, message: String) {
        showMessage.postValue(MessageModel(show, title, message))
    }

    fun message(): MutableLiveData<MessageModel> {
        return showMessage
    }

    private fun showLoading(show: Boolean) {
        showLoading.postValue(show)
    }

    fun loading(): MutableLiveData<Boolean> {
        return showLoading
    }

    fun itemClicked(position: Int) {
        if (list != null) {
            showToast.value = list!![position].code
        }
    }

    fun showToast(): MutableLiveData<String> {
        return showToast
    }

    companion object {
        private const val MAIN_REQUEST = "main_list"
    }
}
