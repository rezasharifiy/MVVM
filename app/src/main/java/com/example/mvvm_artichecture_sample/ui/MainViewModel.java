package com.example.mvvm_artichecture_sample.ui;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm_artichecture_sample.R;
import com.example.mvvm_artichecture_sample.base.BaseViewModel;
import com.example.mvvm_artichecture_sample.base.model.MessageModel;
import com.example.mvvm_artichecture_sample.base.network.baseobserver.ErrorConsumer;
import com.example.mvvm_artichecture_sample.base.network.baseobserver.SuccessConsumer;
import com.example.mvvm_artichecture_sample.base.network.baseobserver.WebServiceListener;
import com.example.mvvm_artichecture_sample.base.network.model.APIError;
import com.example.mvvm_artichecture_sample.data.MainRepository;
import com.example.mvvm_artichecture_sample.data.remote.apimodel.CountryModel;
import com.example.mvvm_artichecture_sample.data.remote.apimodel.ReposnseModel;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends BaseViewModel<MainRepository> implements WebServiceListener {
	
	
	private MutableLiveData<Boolean> showLoadig = new MutableLiveData<>();
	private MutableLiveData<MessageModel> showMessage = new MutableLiveData<>();
	private MutableLiveData<List<CountryModel>> countryList = new MutableLiveData<>();
	private static final String MAIN_REQUEST = "main_list";
	private MutableLiveData<String> showToast = new MutableLiveData<>();
	private List<CountryModel> list;
	
	
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
			showMessage(true, getString(R.string.error), getString(R.string.internet_error));
		}
		
		
	}
	
	private void setList(List<CountryModel> list) {
		countryList.setValue(list);
	}
	
	public MutableLiveData<List<CountryModel>> updateList() {
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
		list = ((ReposnseModel) object).getCountryModel();
		setList(list);
	}
	
	public MutableLiveData<String> showToast() {
		return showToast;
	}
	
	@Override
	public void onError(APIError apiError, int statusCode, String requestType) throws Exception {
		showLoading(false);
		showMessage(true, getString(R.string.error), apiError != null ? apiError.getMessage() : getString(R.string.errorRecieveData));
	}
	
	public void itemClicked(int position) {
		if (list != null)
			showToast.setValue(String.valueOf(list.get(position).getCode()));
	}
}
