package lanou.foodpies.goingeats.knowledge;

import android.util.Log;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.ArrayList;

import lanou.foodpies.R;
import lanou.foodpies.base.BaseFragment;
import lanou.foodpies.beans.KnowledgeBean;
import lanou.foodpies.tools.GsonRequest;
import lanou.foodpies.tools.VolleySingleton;
import lanou.foodpies.urls.UriLines;

/**
 * Created by XiaoyuLu on 16/11/3.
 *
 * 这是 逛吃选项 中的 第三项: 知识
 */
public class KnowledgeFragment extends BaseFragment {

    private ListView listView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_knowledge;
    }

    @Override
    protected void initViews() {

        listView = bindView(R.id.eat_knowledge_list);
    }

    @Override
    protected void initData() {

        GsonRequest<KnowledgeBean> gsonRequest = new GsonRequest<KnowledgeBean>(
                KnowledgeBean.class, UriLines.EAT_KNOWLEDGE_URL,
                new Response.Listener<KnowledgeBean>() {
                    @Override
                    public void onResponse(KnowledgeBean response) {


                        Log.d("KnowledgeFragment", "response:" + response);
                        ArrayList<KnowledgeBean.FeedsBean> feedsBeanArrayList =
                                (ArrayList<KnowledgeBean.FeedsBean>) response.getFeeds();

                        MyKnowLedgeLvAdapter adapter = new MyKnowLedgeLvAdapter();
                        adapter.setFeedsBeanArrayList(feedsBeanArrayList);
                        listView.setAdapter(adapter);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // 请求失败
                        Log.d("KnowledgeFragment", "数据请求失败啦!");
                    }
                }
        );
        VolleySingleton.getInstance().addRequest(gsonRequest);

    }
}
