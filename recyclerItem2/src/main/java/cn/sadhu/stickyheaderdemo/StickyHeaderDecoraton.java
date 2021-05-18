package cn.sadhu.stickyheaderdemo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * Created by sadhu on 2016/9/23.
 * Email static.sadhu@gmail.com
 */
public class StickyHeaderDecoraton extends RecyclerView.ItemDecoration {

    public static final String TAG = "StickyHeaderDecoraton";
    private float mDividerHeight;
    private Paint mPaint;
    private Paint mTextPaint;
    private List<AppInfoBean> mDatas;

    public StickyHeaderDecoraton(int height, Context ctx) {
        this(height, Color.GRAY, ctx);
    }


    public StickyHeaderDecoraton(int height, @ColorInt int color, Context ctx) {
        this.mDividerHeight = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, height, ctx.getResources().getDisplayMetrics());
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);


        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 14, ctx.getResources().getDisplayMetrics()));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // 如果是头部,留出顶部空间用作画Header
        if (((SectionBean) view.getTag()).isGroupStart) {
            outRect.set(0, (int) mDividerHeight, 0, 0);
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            // 如果是头,画之
            if (position != RecyclerView.NO_POSITION
                    && ((SectionBean) view.getTag()).isGroupStart) {
                drawHeader(c, parent, view, position);
            }
        }
    }

    /**
     * 画头部
     *
     * @param c
     * @param parent
     * @param view
     * @param position
     */
    private void drawHeader(Canvas c, RecyclerView parent, View view, int position) {
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();
        int bottoom = view.getTop() - params.topMargin - Math.round(ViewCompat.getTranslationY(view));
        int top = (int) (bottoom - mDividerHeight);
        // 计算文字居中时候的基线
        Rect targetRect = new Rect(left, top, right, bottoom);
        Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
        int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        c.drawRect(left, top, right, bottoom, mPaint);
        c.drawText(mDatas.get(position).tag, left, baseline, mTextPaint);
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        View view = parent.getChildAt(0);
        View view2 = parent.getChildAt(1);
        if (view != null && view2 != null) {
            SectionBean section1 = (SectionBean) view.getTag();
            SectionBean section2 = (SectionBean) view2.getTag();

            int position = parent.getChildAdapterPosition(view);
            final int left = parent.getPaddingLeft();
            final int right = parent.getWidth() - parent.getPaddingRight();
            int bottoom = (int) mDividerHeight;
            int top = 0;
            // 判断是否达到临界点
            // (第一个可见item是每组的最后一个,第二个可见tiem是下一组的第一个,并且第一个可见item的底部小于header的高度)
            // 这里直接判断item的底部位置小于header的高度有点欠妥,应该还要考虑paddingtop以及margintop,这里展示不考虑了
            if (section1.isGroupEnd && section2.isGroupStart && view.getBottom() <= mDividerHeight) {
                bottoom = view.getBottom();
                top = (int) (bottoom - mDividerHeight);
            }
            // 计算文字居中时候的基线
            Rect targetRect = new Rect(left, top, right, bottoom);
            Paint.FontMetricsInt fontMetrics = mTextPaint.getFontMetricsInt();
            int baseline = (targetRect.bottom + targetRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
            // 背景
            c.drawRect(left, top, right, bottoom, mPaint);
            // 文字
            c.drawText(mDatas.get(position).tag, left, baseline, mTextPaint);
        }
    }

    /**
     * 头部的名称
     *
     * @param mDatas
     */
    public void setDatas(List<AppInfoBean> mDatas) {
        this.mDatas = mDatas;
    }
}
