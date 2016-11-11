package lanou.foodpies;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import lanou.foodpies.base.BaseActivity;
import lanou.foodpies.foodlibrarys.LibraryFragment;
import lanou.foodpies.goingeats.EatFragment;
import lanou.foodpies.mine.MyFragment;

public class MainActivity extends BaseActivity {

    private RadioGroup radioGroup;
    private RadioButton radioButton;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        initViewsId();

    }

//    控件id 布局替换
    protected void initViewsId () {
        radioGroup = (RadioGroup) findViewById(R.id.main_radiogroup);
        radioButton = (RadioButton) findViewById(R.id.main_radio_library);
        radioButton.setChecked(true);
//        获取管理者权限替换布局
        FragmentTransaction manager = getSupportFragmentManager().beginTransaction();
        manager.replace(R.id.main_framelayout, new LibraryFragment());
        manager.commit();
//        设置radioGroup的监听事件
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                judgeRadioButtonIsChecked(i);
            }
        });

        Log.d("MainActivity", "获取控件id");
    }

   /**  被initViewsId调用  FrameLayout布局替换Fragment */
   protected void judgeRadioButtonIsChecked(int checkedId) {

       FragmentManager manager = getSupportFragmentManager();
       FragmentTransaction transaction = manager.beginTransaction();

       switch (checkedId) {
           case R.id.main_radio_library:
               transaction.replace(R.id.main_framelayout, new LibraryFragment());

               break;
           case R.id.main_radio_homepage:
               transaction.replace(R.id.main_framelayout, new EatFragment());

               break;
           case R.id.main_radio_my:
               transaction.replace(R.id.main_framelayout, new MyFragment());

               break;
           default:
               break;
       }
       transaction.commit();
       Log.d("MainActivity", "替换布局");

   }

    @Override
    protected void initData() {

    }
}
