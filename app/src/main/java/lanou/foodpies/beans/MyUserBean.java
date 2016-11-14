package lanou.foodpies.beans;

import cn.bmob.v3.BmobUser;

/**
 * Created by dllo on 16/11/14.
 */
public class MyUserBean extends BmobUser{
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
