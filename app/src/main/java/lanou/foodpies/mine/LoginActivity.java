package lanou.foodpies.mine;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.PlatformDb;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.tencent.qq.QQ;
import lanou.foodpies.R;
import lanou.foodpies.base.BaseActivity;
import lanou.foodpies.zhuce.ZhuCeActivity;

/**
 * Created by XiaoyuLu on 16/11/5.
 *
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener{
    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initViews() {
        initViewID();
        ShareSDK.initSDK(this, "18f5c2f378304");


    }

    @Override
    protected void initData() {

    }

//    控件
    protected void initViewID () {
        ImageButton returnImgBtn = bindView(R.id.my_login_return);
        Button qqBtn = bindView(R.id.my_login_qq);
        Button weChatBtn = bindView(R.id.my_login_wechat);
        Button weBoBtn = bindView(R.id.my_login_weibo);
        Button booHeeBtn = bindView(R.id.my_login_boohee);

        setClick(this, returnImgBtn);
        setClick(this, qqBtn, weChatBtn, weBoBtn, booHeeBtn);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_login_return:
                // 点击 返回箭头
                finish();

                break;


            case R.id.my_login_qq:
                // 进行 QQ登录
                Platform qq = ShareSDK.getPlatform(QQ.NAME);
                qq.authorize();

                qq.setPlatformActionListener(new PlatformActionListener() {
                    @Override
                    public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                        PlatformDb db = platform.getDb();
                        String name = db.getUserName();
                        String icon = db.getUserIcon();
                        Log.d("MainActivity", name + icon);
                    }

                    @Override
                    public void onError(Platform platform, int i, Throwable throwable) {

                    }

                    @Override
                    public void onCancel(Platform platform, int i) {

                    }
                });
                Toast.makeText(this, "QQ登录", Toast.LENGTH_SHORT).show();
                Log.d("LoginActivity", "QQ登录");

                break;


            case R.id.my_login_wechat:
                // 进行 微信登录
                Toast.makeText(this, "微信登录", Toast.LENGTH_SHORT).show();
                Log.d("LoginActivity", "微信登录");

                break;
            case R.id.my_login_weibo:
                // 进行 微博登录
                Toast.makeText(this, "微博登录", Toast.LENGTH_SHORT).show();
                Log.d("LoginActivity", "微博登录");

                break;
            case R.id.my_login_boohee:

                Intent intent = new Intent(LoginActivity.this, ZhuCeActivity.class);
                startActivity(intent);

                // 进行薄荷登录
                Toast.makeText(this, "薄荷登录", Toast.LENGTH_SHORT).show();
                Log.d("LoginActivity", "薄荷登录");

                break;
            default:
                Log.d("LoginActivity", "出错啦!");
        }
    }
}
