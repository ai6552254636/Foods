package lanou.foodpies.goingeats.homepage;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.LinearLayout;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import lanou.foodpies.R;
import lanou.foodpies.base.BaseFragment;
import lanou.foodpies.beans.HomepageBean;
import lanou.foodpies.tools.GsonRequest;
import lanou.foodpies.tools.VolleySingleton;
import lanou.foodpies.urls.UriLines;

/**
 * Created by XiaoyuLu on 16/11/3.
 *
 * 这是 狂吃选项 中的 第一项: 首页
 */
public class HomePageFragment extends BaseFragment {


    private RecyclerView recyclerView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_homepage;
    }

    @Override
    protected void initViews() {
        recyclerView = bindView(R.id.eat_homepage_rv);

    }

    @Override
    protected void initData() {
        gsonMethod();
    }

    /** 在该方法中实现 Gson 的相关操作 */
    private void gsonMethod() {
        GsonRequest<HomepageBean> gsonRequest = new GsonRequest<HomepageBean>(
                HomepageBean.class, UriLines.EAT_HOMEPAGE_URL,
                new Response.Listener<HomepageBean>() {
                    @Override
                    public void onResponse(HomepageBean response) {
                        // 网络请求成功
                        Log.d("HomePageFragment", "response:" + response);
                        ArrayList<HomepageBean.FeedsBean> feedsBeanArrayList =
                                (ArrayList<HomepageBean.FeedsBean>) response.getFeeds();

                        MyHomepageRvAdapter adapter = new MyHomepageRvAdapter();
                        adapter.setFeedsBeanArrayList(feedsBeanArrayList);
                        recyclerView.setAdapter(adapter);

//                        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
                        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(
                                2, LinearLayout.VERTICAL);
                        recyclerView.setLayoutManager(manager);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // 请求失败
            }
        });

        VolleySingleton.getInstance().addRequest(gsonRequest);
    }
}

