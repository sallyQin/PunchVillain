package com.example.apc.punchvillain;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.apc.punchvillain.Data.DatabasePropSelect;

import java.util.List;


/**
 * Created by Sally on 2017/1/10.
 */

public class PropSelectAdapter extends UniversalAdapter  {  //recyclerView“选择道具”的适配器

    static PunchVillainActivity punchVillainActivity;
    List<DatabasePropSelect> list;
    Integer propPicDrawable;
    Integer propScore_saved;
    Integer soundGetPosition_saved;

    PropSelectAdapter() {
        super(R.layout.recyclerview_prop_choose);
        list = DatabasePropSelect.getPropList();
        PunchVillainView.propSelectAdapter  = this;
    }

    @Override
    public void onBindViewHolder(UniversalHolder holder, int position) {  //需要重写的部分
        DatabasePropSelect database = list.get(position);
        TextView prop_text  = (TextView) holder.itemView.findViewById(R.id.prop_text);
        ImageView prop_pic  = (ImageView) holder.itemView.findViewById(R.id.prop_image);
        prop_pic.setImageResource(punchVillainActivity.getResources().getIdentifier(database.prop_pic,"drawable",punchVillainActivity.getPackageName()));
        prop_text.setText(database.propName);

        if (position == mSelected) { //设置itewView点中，背景替换。
            holder.itemView.setBackgroundResource(R.color.colorSelect);
        } else {
            holder.itemView.setBackgroundResource(0);
        }
    }

    @Override
    public int getItemCount() { //需要重写的部分
        return list.size();
    }

    @Override
    public void onItemChanged(int position) {  //如需对itemView做具体的进一步操作，则需要重写的部分；否则， onItemChanged(int position)不用写。
         DatabasePropSelect databasePropSelect = list.get(position);
         propPicDrawable = databasePropSelect.punchPropPic;//点击itemView时，获取相应的prop。
         propScore_saved = databasePropSelect.prop_score; //点击itemView时，获取相应的道具分数值。
         soundGetPosition_saved = databasePropSelect.soundGetPosition;////点击itemView时，获取相应的音效资源。



    }

    }


