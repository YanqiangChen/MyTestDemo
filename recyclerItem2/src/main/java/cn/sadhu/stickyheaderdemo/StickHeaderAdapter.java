package cn.sadhu.stickyheaderdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * Created by sadhu on 2016/9/23.
 * Email static.sadhu@gmail.com
 */
public class StickHeaderAdapter extends RecyclerView.Adapter<StickHeaderAdapter.StickHeaderVH> {

    private LayoutInflater mInflater;
    private List<AppInfoBean> mDatas;

    public StickHeaderAdapter(List<AppInfoBean> datas) {
        this.mDatas = datas;
    }

    @Override
    public StickHeaderVH onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mInflater == null) {
            mInflater = LayoutInflater.from(parent.getContext());
        }
        return new StickHeaderVH(mInflater.inflate(R.layout.item_app_info, parent, false));
    }

    @Override
    public void onBindViewHolder(StickHeaderVH holder, int position) {
        holder.sdvPic.setImageURI(mDatas.get(position).url);
        holder.tvTitle.setText(mDatas.get(position).name);
        generateTag(holder, position);

    }

    private void generateTag(StickHeaderVH holder, int position) {
        SectionBean tag;
        // 没有tag的话 new 一个, 有的话 复用
        if (holder.itemView.getTag() == null) {
            tag = new SectionBean();
        } else {
            tag = (SectionBean) holder.itemView.getTag();
        }
        // 判断当前position的开始结束状态
        if (position == 0) {
            tag.isGroupStart = true;
            tag.isGroupEnd = !mDatas.get(position).tag.equals(mDatas.get(position + 1).tag);
        } else if (position == mDatas.size() - 1) {
            tag.isGroupStart = !mDatas.get(position).tag.equals(mDatas.get(position - 1).tag);
            tag.isGroupEnd = true;
        } else {
            tag.isGroupStart = !mDatas.get(position).tag.equals(mDatas.get(position - 1).tag);
            tag.isGroupEnd = !mDatas.get(position).tag.equals(mDatas.get(position + 1).tag);
        }
        holder.itemView.setTag(tag);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class StickHeaderVH extends RecyclerView.ViewHolder {
        SimpleDraweeView sdvPic;
        TextView tvTitle;

        StickHeaderVH(View itemView) {
            super(itemView);
            sdvPic = (SimpleDraweeView) itemView.findViewById(R.id.sdvPic);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
        }
    }
}
