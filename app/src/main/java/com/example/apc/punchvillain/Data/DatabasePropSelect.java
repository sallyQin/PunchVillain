package com.example.apc.punchvillain.Data;

import com.example.apc.punchvillain.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 1 on 2017/1/10.
 */

public class DatabasePropSelect {

    public String prop_pic;//道具图片
    public String propName;//道具名称
    public Integer punchPropPic;//开打的道具图
    public Integer prop_score; //道具分值
    public Integer soundGetPosition;//音效资源位置


    public DatabasePropSelect(String prop_pic, String propName,Integer punchPropPic,Integer prop_score,Integer soundGetPosition ){

            this.prop_pic = prop_pic;
            this.propName = propName;
            this.punchPropPic = punchPropPic;
            this.prop_score = prop_score;
            this.soundGetPosition = soundGetPosition;
    }

    public static Map getAttackProp (){  //出击时，对应的prop图
        Map<String, String>  attackProp_map = new HashMap<>();
        attackProp_map.put("图钉","pin_prop");
        attackProp_map.put("拳头","fist_prop");
        attackProp_map.put("巴掌","palm_print_prop");
        attackProp_map.put("左勾拳","left_glove_prop");
        attackProp_map.put("右勾拳","right_glove_prop");
        attackProp_map.put("狗粑粑","shit_prop");
        attackProp_map.put("鞋底","footprint_prop");
        attackProp_map.put("飞镖","darts_prop");
        attackProp_map.put("斧子","blood_knife_prop");
        attackProp_map.put("火掌","fire_palm_prop");
        attackProp_map.put("喷墨枪","color_prop");
        attackProp_map.put("衰符","slogan_prop");
        return  attackProp_map;

    }


    public static List<DatabasePropSelect> getPropList(){
        List<DatabasePropSelect> list = new ArrayList<>();
        list.add(new DatabasePropSelect("propv","图钉-2分", R.drawable.pin_prop,2,6));
        list.add(new DatabasePropSelect("prop1","拳头-1分",R.drawable.fist_prop,1,3));
        list.add(new DatabasePropSelect("prop2","巴掌-1分",R.drawable.palm_print_prop,1,5));
        list.add(new DatabasePropSelect("prop3","左勾拳-2分",R.drawable.left_glove_prop,2,3));
        list.add(new DatabasePropSelect("prop4","右勾拳-2分",R.drawable.right_glove_prop,2,3));
        list.add(new DatabasePropSelect("prop5","狗粑粑-1分",R.drawable.shit_prop,1,6));
        list.add(new DatabasePropSelect("prop_6","鞋底-1分",R.drawable.footprint_prop,1,6));
        list.add(new DatabasePropSelect("prop7","飞镖-2分",R.drawable.darts_prop,2,2));
        list.add(new DatabasePropSelect("prop8","斧子-5分",R.drawable.blood_knife_prop,5,1));
        list.add(new DatabasePropSelect("prop9","火掌-5分",R.drawable.fire_palm_prop,5,8));
        list.add(new DatabasePropSelect("prop10","喷墨枪-1分",R.drawable.color_prop,1,4));
        list.add(new DatabasePropSelect("prop11","衰符-1分",R.drawable.slogan_prop,1,7));
        return  list;
    }
}
