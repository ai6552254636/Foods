package lanou.foodpies.mine.collection;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import lanou.foodpies.R;
import lanou.foodpies.base.CommonVH;
import lanou.foodpies.beans.CollectionSqlDataBean;

/**
 * Created by XiaoyuLu on 16/11/14.
 *
 * 显示收藏中文章的数据
 */
public class MyCollectionArticleLVAdapter extends BaseAdapter {

    private ArrayList<CollectionSqlDataBean> arrayList;

    public void setArrayList(ArrayList<CollectionSqlDataBean> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        CommonVH commonVH = CommonVH.getViewHolder(convertView, parent, R.layout.item_collection_artical);
        String title = arrayList.get(position).getTitle();
        commonVH.setText(R.id.item_collection_article_tv, title);

        return commonVH.getItemView();
    }
}
