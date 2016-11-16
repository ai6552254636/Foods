package lanou.foodpies.zhuce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

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


public class ZhuCeActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtnDL;
    private Button mBtnZZ;
    private EditText mEdtZH;
    private EditText mEdtMM;
    private CheckBox mCheckBox1;
    private CheckBox mCheckBox2;
    private SharedPreferences.Editor editor;
    private ImageButton mImagBtn;

//    private String getUsername; // 输入的用户名
//    private String getPassword; // 密码


    @Override
    protected int getLayout() {
        return R.layout.activity_zhu_ce ;
    }

    @Override
    protected void initViews() {

        initViewsId();
        spvoid();
    }

//    获取控件id
    protected void initViewsId () {
        mBtnDL = bindView(R.id.btn_denglu);
        mBtnZZ = bindView(R.id.btn_zhuce);
        mEdtZH = bindView(R.id.edt_zhanghao);
        mEdtMM = bindView(R.id.edt_mima);
        mCheckBox1 = bindView(R.id.checkBox1);
        mCheckBox2 = bindView(R.id.checkBox2);
        mImagBtn = bindView(R.id.cancel);

        mBtnDL.setOnClickListener(this);
        mBtnZZ.setOnClickListener(this);
        mImagBtn.setOnClickListener(this);
        mCheckBox2.setOnClickListener(this);

    }


//    用户名数据持久化的方式
    protected void spvoid () {
        SharedPreferences preferences = getSharedPreferences("UserInfo",MODE_PRIVATE);
        editor = preferences.edit();

        /**
         * 启动程序时首先检查sharedPreferences中是否储存有用户名和密码
         若无，则将checkbox状态显示为未选中
         若有，则直接中sharedPreferences中读取用户名，并将checkbox状态显示为已选中
         这里getString()方法需要两个参数，第一个是键，第二个是值。
         启动程序时我们传入需要读取的键，值填null即可。若有值则会自动显示，没有则为空。
         */

//        判断框内是否有账号显示 是否勾选CheckBox
        String name = preferences.getString("userName" , null);
        if (name == null) {
            mCheckBox1.setChecked(false);
        } else {
            mEdtZH.setText(name);
            mCheckBox1.setChecked(true);
        }
    }


//    点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_denglu:
                login();
                break;
            case R.id.btn_zhuce:
                createuser();
                break;
            case R.id.cancel:
                finish();
                break;
            case R.id.checkBox2:
                //        判断框内密码是否显示   是否勾选CheckBox
                if(mCheckBox2.isChecked()) {
            mEdtMM.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    Toast.makeText(this, "选中", Toast.LENGTH_SHORT).show();
                } else {
            mEdtMM.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    Toast.makeText(this, "未选中", Toast.LENGTH_SHORT).show();
                }

                break;
            default:
                break;
        }
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


//    登录方法
    protected void login () {
       BmobUser bmobUser = new BmobUser();
        bmobUser.setUsername(mEdtZH.getText().toString());
        bmobUser.setPassword(mEdtMM.getText().toString());
        bmobUser.login(new SaveListener<MyUserBean>() {
           @Override
           public void done(MyUserBean myUserBean, BmobException e) {
                if (e == null) {
                    //跳转到第三个Activity然后在那边执行onRestart方法mainfest注册Activity的启动模式为singletask
                    Intent intent = new Intent(ZhuCeActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(ZhuCeActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    //如果用户选择了记住用户名
                    //将用户输入的用户名存入储存中，键为userName
                    if (mCheckBox1.isChecked()) {
                        editor.putString("userName", mEdtZH.getText().toString());
                        editor.commit();
                    } else {
                        //否则用户名将被清除
                        editor.remove("userName");
                        editor.commit();
                    }
                    //外部判断,如果用户名登录密码正确可以正常登录,
                    //否则登录失败清空密码,重新登录
                } else {
                    editor.remove("userPassword");
                    editor.commit();
                    Toast.makeText(ZhuCeActivity.this, "登录失败,请重新输入", Toast.LENGTH_SHORT).show();
                }
           }
       });
   }


//    上传头像的方法
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


    @Override
    protected void initData() {

    }

}
