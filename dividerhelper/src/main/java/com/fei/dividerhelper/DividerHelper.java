package com.fei.dividerhelper;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 创建日期：2018/1/19 on 15:02
 * 描述:
 * 作者:Li
 * @author Li
 */

public class DividerHelper  {

    private DrawableProvider drawableProvider;
    /**
     * 是否显示第一个item的顶部分割线
     */
    private boolean showTop = false;
    /**
     * 是否显示最后一个item的底部分割线
     **/
    private boolean showBottom = false;
    private int     dividerHeight = 0;
    private int     dividerWidth = 0;
    private int      marginLeft = 0;
    private int      marginRight = 0;

    public DividerHelper(){

    }



    public boolean showTop() {
        return showTop;
    }

    public boolean showBottom() {
        return showBottom;
    }





    public Drawable getDivider(int position ,RecyclerView recyclerView) {
        return drawableProvider.getDivider(position,recyclerView);
    }

    public int getDividerHeight(int position ,RecyclerView recyclerView) {
        Drawable dividerDrawable = getDivider(position,recyclerView);
        if (dividerDrawable == null){
            return dividerHeight;
        }else {
            if (dividerDrawable.getIntrinsicHeight() <= 0){
                return dividerHeight;
            }else {
                return dividerDrawable.getIntrinsicHeight();
            }
        }
    }

    public int getDividerWidth(int position ,RecyclerView recyclerView) {
        Drawable dividerDrawable = getDivider(position,recyclerView);
        if (dividerDrawable == null){
            return dividerWidth;
        }else {
            if (dividerDrawable.getIntrinsicWidth() <= 0){
                return dividerWidth;
            }else {
                return dividerDrawable.getIntrinsicWidth();
            }
        }
    }

    public void drawTop(int position,Canvas c, RecyclerView parent, View child) {
        Drawable mDivider = getDivider(position,parent);
        if (mDivider == null){
            return;
        }
        Rect rect = boundsTop(position,parent,child);

        mDivider.setBounds(rect);
        mDivider.draw(c);
    }

    public void drawBottom(int position,Canvas c, RecyclerView parent, View child) {
        if (position < 0){
            return;
        }
        int totalCount  = parent.getAdapter().getItemCount();

        Drawable mDivider = null;
        if (position == totalCount-1 ){
            //最后一个
            if (showBottom){

                    mDivider = getDivider(position,parent);
            }
        }else {
            //正常的
            mDivider = getDivider(position,parent);

        }

        if (mDivider == null){
            return;
        }

        Rect rect = boundsBottom(position,parent,child);

        mDivider.setBounds(rect);
        mDivider.draw(c);
    }



    public void drawLeft(int position,Canvas c, RecyclerView parent, View child) {
        Drawable mDivider = getDivider(position,parent);
        if (mDivider == null){
            return;
        }

        Rect rect = boundsLeft(position,parent,child);

        mDivider.setBounds(rect);
        mDivider.draw(c);
    }

    public void drawRight(int position,Canvas c, RecyclerView parent, View child) {

        Drawable mDivider = getDivider(position,parent);
        if (mDivider == null){
            return;
        }

        Rect rect = boundsRight(position,parent,child);

        mDivider.setBounds(rect);
        mDivider.draw(c);
    }




    private Rect boundsBottom(int position,RecyclerView parent, View child){
        Rect bounds = new Rect(0, 0, 0, 0);
        int transitionX = (int) child.getTranslationX();
        int transitionY = (int) child.getTranslationY();
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

        int dividerSize = getDividerHeight(position,parent);

        bounds.left = parent.getPaddingLeft() + transitionX + marginLeft;
        bounds.right = parent.getWidth() - parent.getPaddingRight() + transitionX - marginRight;

        bounds.top = child.getBottom() + params.bottomMargin + transitionY;
        bounds.bottom = bounds.top + dividerSize;
        return bounds;
    }


    private Rect boundsTop(int position,RecyclerView parent, View child){
        Rect bounds = new Rect(0, 0, 0, 0);
        int transitionX = (int) child.getTranslationX();
        int transitionY = (int) child.getTranslationY();
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

        int dividerSize = getDividerHeight(position,parent);

        bounds.left = parent.getPaddingLeft() + transitionX + marginLeft;
        bounds.right = parent.getWidth() - parent.getPaddingRight() + transitionX -marginRight;

        bounds.top = child.getTop() - params.topMargin + transitionY-dividerSize;
        bounds.bottom = child.getTop();
        return bounds;
    }

    private Rect boundsLeft(int position,RecyclerView parent, View child){
        Rect bounds = new Rect(0, 0, 0, 0);
        int transitionX = (int) child.getTranslationX();
        int transitionY = (int) child.getTranslationY();
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

        int dividerSize = getDividerWidth(position,parent);

        bounds.left = child.getLeft() + transitionX - dividerSize - params.leftMargin;
        bounds.right = child.getLeft() + transitionX ;

        bounds.top = child.getTop()+ transitionY + parent.getPaddingTop();
        bounds.bottom = child.getBottom()  + transitionY - parent.getPaddingBottom();
        return bounds;
    }


    private Rect boundsRight(int position,RecyclerView parent, View child){
        Rect bounds = new Rect(0, 0, 0, 0);
        int transitionX = (int) child.getTranslationX();
        int transitionY = (int) child.getTranslationY();
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

        int dividerSize = getDividerWidth(position,parent);

        bounds.left = child.getRight() + transitionX + params.rightMargin;
        bounds.right = child.getRight() + transitionX + params.rightMargin + dividerSize;

        bounds.top = child.getTop()+ transitionY + parent.getPaddingTop();
        bounds.bottom = child.getBottom()  + transitionY - parent.getPaddingBottom();
        return bounds;
    }


    public void setShowBottom(boolean showBottom) {
        this.showBottom = showBottom;
    }

    public void setShowTop(boolean showTop) {
        this.showTop = showTop;
    }


    public void setDrawableProvider(DrawableProvider drawableProvider) {
        this.drawableProvider = drawableProvider;
    }

    public void setDividerHeight(int dividerHeight) {
        this.dividerHeight = dividerHeight;
    }

    public void setDividerWidth(int dividerWidth) {
        this.dividerWidth = dividerWidth;
    }

    public void setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
    }

    public void setMarginRight(int marginRight) {
        this.marginRight = marginRight;
    }

    public static class Builder{
        private int defaultHeightWidth = 1;
        private int defaultMargin = 0;
        DividerHelper helper ;
        public Builder(){
            helper = new DividerHelper();
            helper.setShowTop(false);
            helper.setShowBottom(false);
            helper.setDividerHeight(defaultHeightWidth);
            helper.setDividerWidth(defaultHeightWidth);
            helper.setMarginLeft(defaultMargin);
            helper.setMarginRight(defaultMargin);
        }

        public SimpleItemDecoration build(){
            SimpleItemDecoration simpleItemDecoration = new SimpleItemDecoration(helper);
            return simpleItemDecoration;
        }


        public Builder showTop(boolean showTop){
            helper.setShowTop(showTop);
            return this;
        }


        public Builder showBottom(boolean showBottom){
            helper.setShowBottom(showBottom);

            return this;
        }


        public Builder divider(Drawable drawable){
            helper.setDrawableProvider(new DefaultDrawableProvider(drawable));

            return this;
        }

        public Builder divider(int  color){
            helper.setDrawableProvider(new DefaultDrawableProvider(new ColorDrawable(color)));
            return this;
        }

        public Builder divider(DrawableProvider provider){
            helper.setDrawableProvider(provider);
            return this;
        }

        public Builder height(int  height){
            helper.setDividerHeight(height);
            return this;
        }
        public Builder width(int  width){
            helper.setDividerWidth(width);
            return this;
        }

        public Builder marginLeft(int marginLeft){
            helper.setMarginLeft(marginLeft);
            return this;
        }

        public Builder marginRight(int marginRight){
            helper.setMarginRight(marginRight);
            return this;
        }
    }

    public interface DrawableProvider{
        /**
         * 根据位置获取对应位置的分割线drawable
         * 如果分割线有很多类型,就要实现这个接口，返回对应的drawable
         * @param position
         * @param recyclerView
         * @return
         */
        Drawable getDivider(int position, RecyclerView recyclerView);
    }



    public static class DefaultDrawableProvider implements DrawableProvider{

        private Drawable drawable ;
        public DefaultDrawableProvider(Drawable drawable){
            this.drawable = drawable;
        }
        @Override
        public Drawable getDivider(int position, RecyclerView recyclerView) {
            return drawable;
        }
    }
}
