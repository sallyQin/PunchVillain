package com.example.apc.punchvillain;

import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Sally on 2016/12/13.
 */
public abstract class UniversalAdapter extends RecyclerView.Adapter<UniversalHolder> implements View.OnClickListener {  //通用适配器

    private int mItemRes;
    int mSelected = -1;

    public UniversalAdapter(@LayoutRes int itemRes) {
        mItemRes = itemRes;
    }

    @Override
    public UniversalHolder onCreateViewHolder(ViewGroup parent, int viewType) {   //*通用
        View view = LayoutInflater.from(parent.getContext()).inflate(mItemRes,parent,false);
        UniversalHolder universalHolder = new UniversalHolder(view);
        view.setTag(universalHolder);//view.setTag()方法是为了把holder记录下来。使得view关联holder，可使View在别处也可以获得holder
        // .因为我们可以通过Holder的方法来获取对应的view,但无法直接从view获取holder，所以要给view设定tag,从而通过view.getTag()获得holder，从而用holder的.getAdapterPosition()获得相应的item的位置。
        view.setOnClickListener(this);
        return universalHolder;
    }

    @Override
    public void onClick(View v) { // *通用
        Object tag = v.getTag();
        if (tag instanceof RecyclerView.ViewHolder) {   // check tag-changed case
            setSelected(((RecyclerView.ViewHolder) tag).getAdapterPosition());
        }
    }

    public void setSelected(int position) { // *通用
        if (position != mSelected) {  //一旦position发生改变，改变界面
            int old = mSelected;
            mSelected = position;
            if (old >= 0) {
                notifyItemChanged(old);
            }
            if (position >= 0) {
                notifyItemChanged(position);
            }
            onItemChanged(position);
        }
    }

    public void onItemChanged(int position) { //具体需要点击itemView做什么操作
    }


}
