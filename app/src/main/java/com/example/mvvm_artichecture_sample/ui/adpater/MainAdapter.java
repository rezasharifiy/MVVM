package com.example.mvvm_artichecture_sample.ui.adpater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_artichecture_sample.R;
import com.example.mvvm_artichecture_sample.data.remote.apimodel.CountryModel;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> implements View.OnClickListener {
	
	private Context mContext;
	private List<CountryModel> mList;
	private WeakReference<MainAdapterHanlder> hanlderWeakReference;
	
	public MainAdapter(Context context, MainAdapterHanlder hanlder) {
		mContext = context;
		hanlderWeakReference = new WeakReference<>(hanlder);
		mList = new ArrayList<>();
	}
	
	public void addItem(List<CountryModel> list) {
		mList.addAll(list);
		notifyDataSetChanged();
	}
	
	@NonNull
	@Override
	public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(mContext).inflate(R.layout.main_item_list, parent, false);
		return new MainViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
		CountryModel model = mList.get(position);
		holder.title.setText(model.getName());
	}
	
	@Override
	public int getItemCount() {
		return mList != null ? mList.size() : 0;
	}
	
	@Override
	public void onClick(View v) {
		MainViewHolder holder = (MainViewHolder) v.getTag();
		int pos = holder.getLayoutPosition();
		clicked(pos);
	}
	
	private void clicked(int position) {
		MainAdapterHanlder handler = hanlderWeakReference.get();
		if (handler != null)
			handler.onItemClick(position);
	}
	
	class MainViewHolder extends RecyclerView.ViewHolder {
		TextView title;
		
		public MainViewHolder(@NonNull View itemView) {
			super(itemView);
			title = itemView.findViewById(R.id.title);
			
			itemView.setTag(this);
			itemView.setOnClickListener(MainAdapter.this);
		}
	}
	
	
}
