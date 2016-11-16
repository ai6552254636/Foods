package lanou.foodpies.foodlibrarys.search;

import android.widget.Toast;

import lanou.foodpies.R;
import lanou.foodpies.base.BaseFragment;

/**
 * Created by XiaoyuLu on 16/11/12.
 *
 * 这是要进行对比时 搜索出来的结果
 */
public class SearchCompareFragment extends BaseFragment {

    private String getTextStr;  // SearchActivity 的输入框的内容

    @Override
    protected int getLayout() {
        return R.layout.fragment_library_search_compare;
    }

    @Override
    protected void initViews() {

        getTextStr = ((SearchActivity)getActivity()).getTextStr();
        Toast.makeText(mContext, getTextStr, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void initData() {

    }
}

