package com.fei.dividerhelper;

/**
 * 创建日期：2018/1/19 on 15:01
 * 描述:
 * 作者:Li
 */

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * v1.0  现在只支持同高的divider
 * @author Li
 */
public  class SimpleItemDecoration extends RecyclerView.ItemDecoration {

    DividerHelper dividerHelper;



    public SimpleItemDecoration(DividerHelper dividerHelper){
        this.dividerHelper = dividerHelper;
    }
    /**
     * 拿到drawable后 给 outRect设置下大小，以后绘制就在这个范围里画
     * 测试了下，设置top和bottom效果一样的
     * update:  top 和bottom 不一样，top是往上,可以画第一个的head getDivider
     *          bottom可以画最后一个的bottom  getDivider
     * update: top bottom left right 指的是item的上下左右边距
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {

        //1 获取对应view的实际位置，然后让子类确定具体需要多大的空间
        if (parent.getChildAdapterPosition(view) ==  RecyclerView.NO_POSITION) {
            return;
        }
        int childPosition = parent.getChildAdapterPosition(view);
        int totalCount = parent.getAdapter() == null ? 0 : parent.getAdapter().getItemCount();


        if (getOrientation(parent) == LinearLayoutManager.VERTICAL) {

            if (dividerHelper.showTop() && childPosition == 0){
                outRect.top = dividerHelper.getDividerHeight(childPosition,parent);
            }

            if ((childPosition == totalCount-1 && dividerHelper.showBottom())
                    || childPosition < totalCount -1){
                outRect.bottom = dividerHelper.getDividerHeight(childPosition,parent);
            }


        } else {
            if (dividerHelper.showTop() && childPosition == 0){
                outRect.left = dividerHelper.getDividerWidth(childPosition,parent);
            }
            if ((childPosition == totalCount-1 && dividerHelper.showBottom())
                    || childPosition < totalCount -1){
                outRect.right = dividerHelper.getDividerWidth(childPosition,parent);
            }
        }

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (parent.getAdapter() == null) {
            return;
        }

        int orientation = getOrientation(parent) ;
        //屏幕上显示的item count
        int validChildCount = parent.getChildCount();
        //1 遍历屏幕上的所有view，确定实际位置
        //2 调用子类，获取对应的位置和分割drawable
        //3 画出来
        for (int i = 0; i < validChildCount; i++) {
            View childView = parent.getChildAt(i);
            //这才是真正的adapter 位置
            int childPosition = parent.getChildAdapterPosition(childView);

            //显示顶部分割线
            if (childPosition == 0 && dividerHelper.showTop()){
                if (orientation == OrientationHelper.VERTICAL){
                    dividerHelper.drawTop(childPosition,c,parent,childView);
                }else {
                    dividerHelper.drawLeft(childPosition,c,parent,childView);
                }
            }

            int totalCount  = parent.getAdapter().getItemCount();

            if ((childPosition == totalCount-1 && dividerHelper.showBottom())||
                    childPosition < totalCount -1){
                if (orientation == OrientationHelper.VERTICAL) {
                    //显示底部分割线
                    dividerHelper.drawBottom(childPosition,c, parent, childView);
                }else {
                    dividerHelper.drawRight(childPosition,c, parent, childView);

                }

            }

        }
    }


    private int getOrientation(RecyclerView parent) {
        if (parent.getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
            return layoutManager.getOrientation();
        } else if(parent.getLayoutManager() instanceof GridLayoutManager){
            GridLayoutManager layoutManager = (GridLayoutManager) parent.getLayoutManager();
            return layoutManager.getOrientation();
        } else {
            throw new IllegalStateException(
                    "DividerItemDecoration can only be used with a LinearLayoutManager.");
        }
    }




}