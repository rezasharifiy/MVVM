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


    private val showLoadig = MutableLiveData<Boolean>()
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
            showLoading(true)
            scope.launch {

                output = repository!!.countries(MAIN_REQUEST)

                when (output) {

                    is Output.Success -> {
                        list = (output as Output.Success<ResponsModel>).reposnse.result
                        setList(list!!)
                    }

                    is Output.Error -> {
                        showMessage(true, getString(R.string.error), (output as Output.Error).apiError.message!!)
                    }

                }

                showLoading(false)
            }
        } else {
            showMessage(true, getString(R.string.error), getString(R.string.internet_error))
        }
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
        showLoadig.postValue(show)
    }

    fun loading(): MutableLiveData<Boolean> {
        return showLoadig
    }

    fun itemClicked(position: Int) {
        if (list != null) {
            showToast.value = list!![position].code.toString()
        }
    }

    fun showToast(): MutableLiveData<String> {
        return showToast
    }

    companion object {
        private val MAIN_REQUEST = "main_list"
    }
}
