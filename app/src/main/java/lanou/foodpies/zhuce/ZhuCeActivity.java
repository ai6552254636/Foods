package lanou.foodpies.zhuce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import lanou.foodpies.MainActivity;
import lanou.foodpies.R;
import lanou.foodpies.base.BaseActivity;
import lanou.foodpies.beans.MyUserBean;
import lanou.foodpies.beans.TextBean;

public class ZhuCeActivity extends BaseActivity {

    private Button mBtnDL;
    private Button mBtnZZ;
    private EditText mEdtZH;
    private EditText mEdtMM;

    @Override
    protected int getLayout() {
        return R.layout.activity_zhu_ce ;
    }

    @Override
    protected void initViews() {
        //第一：默认初始化   建议初始化放到application里
        Bmob.initialize(this, "827b0566258335edfd3669efaa940c45");
        initViewsId();

    }

//    获取控件id
    protected void initViewsId () {
        mBtnDL = bindView(R.id.btn_denglu);
        mBtnZZ = bindView(R.id.btn_zhuce);
        mEdtZH = bindView(R.id.edt_zhanghao);
        mEdtMM = bindView(R.id.edt_mima);

        mBtnDL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                repeatlogin();
                login();  //调用下面登录的方法

            }
        });

        mBtnZZ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createuser();  //掉用下面创建用户注册的方法
            }
        });

    }

//    创建用户
    protected void createuser () {
        BmobUser bmobUser = new BmobUser();

        bmobUser.setUsername(mEdtZH.getText().toString()); // 用户名"asddbbb"
        bmobUser.setPassword(mEdtMM.getText().toString()); // 密码1111111
        bmobUser.signUp(new SaveListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    Toast.makeText(ZhuCeActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                  Log.d("ZhuCeActivity", "注册成功");
              } else {
                  Toast.makeText(ZhuCeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                  Log.d("ZhuCeActivity", e.getMessage());
              }
            }
        });
    }

//    重复登录判断方法
    public void repeatlogin () {
//        尝试自动登录

        BmobUser bmobUser = BmobUser.getCurrentUser();

        if (bmobUser != null) {
            Toast.makeText(this, "已经登录过了", Toast.LENGTH_SHORT).show();
        } else {
            bmobUser = new BmobUser();
            bmobUser.setUsername(mEdtZH.getText().toString());
            bmobUser.setPassword(mEdtMM.getText().toString());
            bmobUser.login(new SaveListener<BmobUser>() {
                @Override
                public void done(BmobUser bmobUser, BmobException e) {
                    if (e == null) {
                        Intent intent = new Intent(ZhuCeActivity.this, MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(ZhuCeActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(ZhuCeActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("ZhuCeActivity", e.getMessage());
                    }
                }
            });
        }
    }
//登录方法
   protected void login () {
       MyUserBean myUserBean = new MyUserBean();
       myUserBean.setUsername(mEdtZH.getText().toString());
       myUserBean.setPassword(mEdtMM.getText().toString());
       myUserBean.login(new SaveListener<MyUserBean>() {
           @Override
           public void done(MyUserBean myUserBean, BmobException e) {
                if (e == null) {
                    Intent intent = new Intent(ZhuCeActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(ZhuCeActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ZhuCeActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
           }
       });
   }

//上传头像的方法
    protected  void upLoadIcon () {
        final MyUserBean myUser = MyUserBean.getCurrentUser(MyUserBean.class);
        if (myUser == null) {
            Toast.makeText(this, "先登录", Toast.LENGTH_SHORT).show();
        } else {
//            已经登录过  上传头像
//            拿到图片的bitmap
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
//            getCacheDir是Android提供的缓存路径
//            位置是包名/cache 该方法Context的方法,可以使用Application的Context
            File cacheDir = getCacheDir();
            if (!cacheDir.exists()) {
//                如果这个路径不存在
                cacheDir.mkdir();   //就创建这个文件夹
            }
//            文件名加上时间为了防止文件名重复
            long time = System.currentTimeMillis();
            File iconFile = new File(cacheDir,myUser.getUsername() + time + ".png");

            if (iconFile.exists()) {
                //如果文件不存在
                try {
//                    创建文件
                    iconFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            try {
//                创建一个文件输出流
                FileOutputStream fileOutputStream = new FileOutputStream(iconFile);
//                参数一存的格式,存的指令
                bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOutputStream);
                try {
                    fileOutputStream.close();
//                图片就存到了File里面了
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            //上传File
            final BmobFile bmobFile = new BmobFile(iconFile);
            //上传
            bmobFile.uploadblock(new UploadFileListener() {
                @Override
                public void done(BmobException e) {
                    if ( e == null ) {
                        Toast.makeText(ZhuCeActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
//                     拿到图片的URL
                        String fileUrl = bmobFile.getFileUrl();
                        Log.d("MainActivity", fileUrl);
//                     把图片的url存储到用户表里
                        myUser.setIcon(fileUrl);
                        myUser.update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null) {
                                    Toast.makeText(ZhuCeActivity.this, "存储url成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(ZhuCeActivity.this, "存储url失败", Toast.LENGTH_SHORT).show();
                                    Log.d("ZhuCeActivity", e.getMessage());
                                }
                            }
                        });


                    } else {
                        Toast.makeText(ZhuCeActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                        Log.d("MainActivity", e.getMessage());
                    }
                }
            });
        }

    }

//将用户输入的账号存储到本地数据库
    protected void memoryAccount () {
        SharedPreferences setini = getSharedPreferences(mEdtZH.getText().toString(), 0);
        SharedPreferences.Editor edit = setini.edit();
        edit.putString(mEdtZH.getText().toString(), "");
        edit.commit();
    }



    @Override
    protected void initData() {

    }
}
