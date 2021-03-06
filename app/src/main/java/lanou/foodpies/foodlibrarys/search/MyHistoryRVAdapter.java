package lanou.foodpies.foodlibrarys.search;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import lanou.foodpies.R;
import lanou.foodpies.base.CommonVH;
import lanou.foodpies.interfaces.OnRecyclerViewItemClickListener;

/**
 * Created by XiaoyuLu on 16/11/12.
 *
 * 用于铺建历史记录里的数据
 */
public class MyHistoryRVAdapter extends RecyclerView.Adapter{
    private ArrayList<String> stringArrayList;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;

    public void setStringArrayList(ArrayList<String> stringArrayList) {
        this.stringArrayList = stringArrayList;
        notifyDataSetChanged();
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonVH commonVH = CommonVH.getViewHolder(parent, R.layout.item_library_search_history);
        return commonVH;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CommonVH commonVH = (CommonVH) holder;
        String string = stringArrayList.get(position);
        commonVH.setText(R.id.item_library_search_history_tv, string);

        commonVH.getItemView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRecyclerViewItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stringArrayList == null ? 0 : stringArrayList.size();
    }
}

