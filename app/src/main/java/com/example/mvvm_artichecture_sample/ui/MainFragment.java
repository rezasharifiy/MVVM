package com.example.mvvm_artichecture_sample.ui;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_artichecture_sample.R;
import com.example.mvvm_artichecture_sample.base.BaseFragment;
import com.example.mvvm_artichecture_sample.base.ViewModelFactory;
import com.example.mvvm_artichecture_sample.base.model.MessageModel;
import com.example.mvvm_artichecture_sample.data.remote.apimodel.CountryModel;
import com.example.mvvm_artichecture_sample.ui.adpater.MainAdapter;
import com.example.mvvm_artichecture_sample.ui.adpater.MainAdapterHanlder;

import java.util.List;

public class MainFragment extends BaseFragment<MainViewModel> implements MainAdapterHanlder {
	
	private RecyclerView mRecyclerView;
	private ProgressBar mainPb;
	private TextView errorTv;
	private MainAdapter mainAdapter;
	
	public static MainFragment getInstance() {
		return new MainFragment();
	}
	
	@Override
	protected MainViewModel getViewModel() {
		return ViewModelProviders.of(this, getFactory()).get(MainViewModel.class);
	}
	
	private ViewModelProvider.Factory getFactory() {
		return new ViewModelFactory(this);
	}
	
	@Override
	protected int getLayoutId() {
		return R.layout.fragment_main;
	}
	
	@Override
	protected void setupView() {
		findView();
		setAdapter();
		fetchData();
		updateList();
		observeLoading();
		observeShowMessage();
		observeShowErrorMessage();
	}
	
	private void updateList() {
		mViewModel.updateList().observe(this, new Observer<List<CountryModel>>() {
			@Override
			public void onChanged(List<CountryModel> list) {
				addList(list);
			}
		});
	}
	
	private void addList(List<CountryModel> list) {
		mainAdapter.addItem(list);
	}
	
	private void fetchData() {
		mViewModel.fechList();
	}
	
	private void observeShowErrorMessage() {
		mViewModel.message().observe(this, new Observer<MessageModel>() {
			@Override
			public void onChanged(MessageModel messageModel) {
				showErrorMessage(messageModel.isShow(), messageModel.getMessage());
			}
		});
	}
	
	private void observeShowMessage() {
		mViewModel.showToast().observe(this, new Observer<String>() {
			@Override
			public void onChanged(String message) {
				showToast(message);
			}
		});
	}
	
	private void observeLoading() {
		mViewModel.loading().observe(this, new Observer<Boolean>() {
			@Override
			public void onChanged(Boolean show) {
				showProgressBar(show);
			}
		});
	}
	
	private void setAdapter() {
		mainAdapter = new MainAdapter(mContext, this);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
		mRecyclerView.setAdapter(mainAdapter);
	}
	
	private void findView() {
		mRecyclerView = currView.findViewById(R.id.main_rv);
		mainPb = currView.findViewById(R.id.main_pb);
		errorTv = currView.findViewById(R.id.main_error_tv);
	}
	
	private void showErrorMessage(boolean show, String message) {
		errorTv.setVisibility(getVisibility(show));
		errorTv.setText(message);
	}
	
	private void showProgressBar(boolean show) {
		mainPb.setVisibility(getVisibility(show));
	}
	
	private int getVisibility(boolean show) {
		return show ? View.VISIBLE : View.GONE;
	}
	
	private void showToast(String message) {
		Toast.makeText(mContext, message, Toast.LENGTH_SHORT).show();
	}
	
	@Override
	public void onItemClick(int position) {
		mViewModel.itemClicked(position);
		
	}
}
