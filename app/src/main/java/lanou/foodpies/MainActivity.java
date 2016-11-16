package lanou.foodpies;

import android.content.Intent;
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
    private RadioButton radioButton2;

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
        radioButton2 = (RadioButton) findViewById(R.id.main_radio_my);
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

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == 1 && requestCode == 2) {
//
//        }
//
//    }

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
//  接受从第三个Activity跳转过来的界面因为mainfest将Activity默认的启动方式
//  修改成了singleTask而Activity的生命周期里面的onCreate方法只走了一次所以跳转过来之后直接执行的是onRestart方法
    @Override
    protected void onRestart() {
        super.onRestart();
        radioButton2.setChecked(true);
    }
}
