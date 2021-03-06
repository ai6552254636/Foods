package lanou.foodpies.urls;

/**
 * Created by dllo on 16/11/10.
 * 接口
 */
public class UrlLines {

    // 首页食物百科接口
    public static String FOOD_FOOD = "http://food.boohee.com/fb/v1/categories/list";

    //逛吃界面--首页接口
    public static String EAT_HOMEPAGE = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=1&per=10";
    //逛吃--首页--下拉刷新--后半段
    public static String EAT_HOMEPAGE_DOWN_AFTER = "&category=1&per=10";

    //逛吃界面--测评接口
    public static String EAT_TEST = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=2&per=10";
    //逛吃--测试--知识--首页--美食--下拉刷新--前半段
    public static String EAT_DOWN_BEFORE = "http://food.boohee.com/fb/v1/feeds/category_feed?page=";
    //逛吃--测试--下拉刷新--后半段
    public static String EAT_TEST_DOWN_AFTER = "&category=2&per=10";

    //逛吃界面--知识接口
    public static String EAT_KNOWLEDGE = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=3&per=10";
    //逛吃--知识--下拉刷新--后半段
    public static String EAT_KNOWLEDGE_DOWN_AFTER ="&category=3&per=10";

    //逛吃界面--美食接口
    public static String EAT_BEAUTY = "http://food.boohee.com/fb/v1/feeds/category_feed?page=1&category=4&per=10";
    //逛吃--美食--下拉刷新--后半段
    public static String EAT_BEAUTY_DOWN_AFTER ="&category=4&per=10";

    //食物百科--详情--营养素排序pop
    public static String FOOD_DESCRIPTION_LINE = "http://food.boohee.com/fb/v1/foods/sort_types";
    //食物百科--详情--下拉刷新--group--前半部分
    public static String FOOD_DESCRIPTION_DOWN_GROUP_BEFORE = "http://food.boohee.com/fb/v1/foods?kind=group&value=";
    //食物百科--详情--下拉刷新--brand--前半部分
    public static String FOOD_DESCRIPTION_DOWN_BRAND_BEFORE = "http://food.boohee.com/fb/v1/foods?kind=brand&value=";
    //食物百科--详情--下拉刷新--restaurant--前半部分
    public static String FOOD_DESCRIPTION_DOWN_RESTAURANT_BEFORE = "http://food.boohee.com/fb/v1/foods?kind=restaurant&value=";
    //食物百科--详情--下拉刷新--后半部分(上边三个通用后边的)
    public static String FOOD_DESCRIPTION_DOWN_AFTER = "&order_by=1&page=";
    /**
     * 详情界面的内容网址和下拉刷新是一样的
     */



}
