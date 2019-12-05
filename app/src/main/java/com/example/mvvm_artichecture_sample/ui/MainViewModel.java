package com.example.mvvm_artichecture_sample.ui;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_artichecture_sample.R;
import com.example.mvvm_artichecture_sample.base.model.MessageModel;
import com.example.mvvm_artichecture_sample.base.BaseViewModel;
import com.example.mvvm_artichecture_sample.base.network.baseobserver.ErrorConsumer;
import com.example.mvvm_artichecture_sample.base.network.baseobserver.SuccessConsumer;
import com.example.mvvm_artichecture_sample.base.network.baseobserver.WebServiceListener;
import com.example.mvvm_artichecture_sample.data.MainRepository;
import com.example.mvvm_artichecture_sample.data.remote.apimodel.ReposnseModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel<MainRepository> implements WebServiceListener {


    private MutableLiveData<Boolean> showLoadig = new MutableLiveData<>();
    private MutableLiveData<MessageModel> showMessage = new MutableLiveData<>();
    private MutableLiveData<List<ReposnseModel>> countryList = new MutableLiveData<>();
    private static final String MAIN_REQUEST = "main_list";


    public MainViewModel(MainRepository repository, Application application) {
        super(application, repository);
    }

    public void fechList() {
        if (isNetworkConnected()) {
            showMessage(false, "", "");
            showLoading(true);
            addDisposable(getRepository().getList().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SuccessConsumer(this, MAIN_REQUEST), new ErrorConsumer(this, MAIN_REQUEST)));
        } else {
            showMessage(true, getString(R.string.erro), getString(R.string.internet_error));
        }


    }

    private void setList(List<ReposnseModel> list) {
        countryList.setValue(list);
    }

    public MutableLiveData<List<ReposnseModel>> updateList() {
        return countryList;
    }

    private void showMessage(boolean show, String title, String message) {
        showMessage.setValue(new MessageModel(show, title, message));
    }

    public MutableLiveData<MessageModel> message() {
        return showMessage;
    }

    private void showLoading(boolean show) {
        showLoadig.setValue(show);
    }

    public MutableLiveData<Boolean> loading() {
        return showLoadig;
    }

    @Override
    public void onSuccess(Object object, int statusCode, String requestType) throws Exception {
        showLoading(false);
        setList((List<ReposnseModel>) object);
    }

    @Override
    public void onError(List object, int statusCode, String requestType) throws Exception {
        showLoading(false);
        showMessage(true, getString(R.string.erro), getString(R.string.errorRecieveData));
    }
}
