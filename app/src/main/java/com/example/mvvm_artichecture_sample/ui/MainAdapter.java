package com.example.mvvm_artichecture_sample.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_artichecture_sample.R;
import com.example.mvvm_artichecture_sample.data.remote.apimodel.CountryModel;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<> {

    private Context mContext;
    private List<CountryModel> mList;

    public MainAdapter(Context context, List<CountryModel> list) {
        mContext = context;
        mList = list;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_item_list,parent,false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {

        CountryModel model=mList.get(position);
        holder.ti
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
      public    TextView title;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
        }
    }
}
