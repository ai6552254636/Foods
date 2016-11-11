package lanou.foodpies.goingeats;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.ImageButton;

import java.util.ArrayList;

import lanou.foodpies.R;
import lanou.foodpies.base.BaseFragment;
import lanou.foodpies.goingeats.evaluate.EvaluateFragment;
import lanou.foodpies.goingeats.food.FoodFragment;
import lanou.foodpies.goingeats.homepage.HomePageFragment;
import lanou.foodpies.goingeats.knowledge.KnowledgeFragment;

/**
 * Created by XiaoyuLu on 16/10/31.
 *
 * 逛吃 选项
 */
public class EatFragment extends BaseFragment {


    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragmentArrayList;
    private ImageButton camera;

    @Override
    protected int getLayout() {
        return R.layout.fragment_eat;
    }

    @Override
    protected void initViews() {
        tabLayout = (TabLayout) getView().findViewById(R.id.eat_tab_layout);
        viewPager = (ViewPager) getView().findViewById(R.id.eat_view_pager);

        camera = bindView(R.id.eat_label_camera);

        fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new HomePageFragment());
        fragmentArrayList.add(new EvaluateFragment());
        fragmentArrayList.add(new KnowledgeFragment());
        fragmentArrayList.add(new FoodFragment());

        MyEatVpAdapter adapter = new MyEatVpAdapter(getFragmentManager());
        adapter.setFragmentArrayList(fragmentArrayList);
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected void initData() {

    }

}
