package lanou.foodpies.mine.collection;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import lanou.foodpies.R;
import lanou.foodpies.base.BaseActivity;

/**
 * Created by XiaoyuLu on 16/11/14.
 *
 * 登陆后 收藏的界面
 */
public class CollectionActivity extends BaseActivity implements View.OnClickListener{

    private ImageView returnIV;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected int getLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initViews() {
        returnIV = bindView(R.id.my_collection_return);
        tabLayout = (TabLayout) findViewById(R.id.my_collection_tab_layout);
        viewPager = (ViewPager) findViewById(R.id.my_collection_vp);

        setClick(this, returnIV);
    }

    @Override
    protected void initData() {
        showTabLayout();

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_collection_return:
                finish();

                break;
            default:
                Log.d("CollectionActivity", "点击出错啦!");
        }
    }

    /** 显示 tabLayout */
    private void showTabLayout() {
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new CollectionArticleFragment());
        fragmentArrayList.add(new CollectionFoodFragment());
        MyCollectionVPAdapter adapter = new MyCollectionVPAdapter(getSupportFragmentManager());
        adapter.setFragmentArrayList(fragmentArrayList);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
