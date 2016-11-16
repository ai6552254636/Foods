package lanou.foodpies.mine;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import lanou.foodpies.R;
import lanou.foodpies.base.BaseActivity;

/**
 * Created by XiaoyuLu on 16/11/4.
 * <p>
 * 设置界面 的Activity
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener {

    private ImageButton returnImgBtn;
    private ListView listView;
    private Button mCancelBtn;   // 当处于登录 状态时, 显示该 退出按钮

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initViews() {
        returnImgBtn = bindView(R.id.my_set_return);
        listView = bindView(R.id.my_set_list);
        mCancelBtn = bindView(R.id.my_set_cancel_login);
        setClick(this, returnImgBtn,mCancelBtn);
    }



    //    判断退出登录按钮是否隐藏
    protected void initVisible () {

        showListView();

//        当处于登录状态时,显示退出登录按钮
        BmobUser bmobUser = BmobUser.getCurrentUser(BmobUser.class);
        if (bmobUser != null) {
            mCancelBtn.setVisibility(View.VISIBLE);
        } else {
            mCancelBtn.setVisibility(View.GONE);
        }

    }

//    显示ListView当中的数据
    private void showListView () {
        String item = "清除缓存";
        String item1 = "给我们提个建议";
        String item2 = "给个评分吧";
        String item3 = "将食物派分享给朋友";

        String[] items = new String[]{item, item1,item2, item3};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(arrayAdapter);
    }

//    点击事件
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_set_return:
                finish();
                break;
            case R.id.my_set_cancel_login:
                Toast.makeText(this, "退出登录", Toast.LENGTH_SHORT).show();
                BmobUser.logOut();
                finish();

                break;
            default:
                Log.d("SettingActivity", "出错啦!");
        }
    }

    @Override
    protected void initData() {
        initVisible();
    }
}
