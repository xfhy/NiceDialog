package com.xfhy.nicedialog.widget;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.xfhy.nicedialog.R;
import com.xfhy.nicedialog.util.SizeUtils;

/**
 * author feiyang
 * create at 2017/9/5 16:22
 * description： NiceDialog的Base
 */
public abstract class BaseNiceDialog extends DialogFragment {

    private static final String MARGIN = "margin";
    private static final String WIDTH = "width";
    private static final String HEIGHT = "height";
    private static final String DIM = "dim_amount";
    private static final String BOTTOM = "show_bottom";
    private static final String CANCEL = "out_cancel";
    private static final String ANIM = "anim_style";
    private static final String LAYOUT = "layout_id";


    /**
     * 默认Dialog灰色背景深浅
     */
    private static final float DEFAULT_DIM_AMOUNT = 0.5f;
    /**
     * Dialog灰色背景深浅
     */
    private float dimAmount = DEFAULT_DIM_AMOUNT;
    /**
     * 是否在底部显示
     */
    private boolean showBottom;
    /**
     * 宽度
     */
    private int width;
    /**
     * 高度
     */
    private int height;
    /**
     * 距离屏幕左右的宽度
     */
    private int margin;
    /**
     * 触摸外部是否取消
     */
    private boolean outCancel;

    /**
     * 进入退出动画
     */
    @StyleRes
    private int animStyle;
    /**
     * 布局id
     */
    @LayoutRes
    protected int layoutId;

    /**
     * 设置布局
     */
    public abstract int initLayoutId();

    /**
     * 方便进行UI操作和dialog的dismiss
     *
     * @param viewHolder viewHolder
     * @param dialog     DialogFragment对象
     */
    public abstract void convertView(DialogViewHolder viewHolder, BaseNiceDialog dialog);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置为无标题
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.NiceDialog);

        //恢复保存的临时数据
        if (savedInstanceState != null) {
            margin = savedInstanceState.getInt(MARGIN);
            width = savedInstanceState.getInt(WIDTH);
            height = savedInstanceState.getInt(HEIGHT);
            dimAmount = savedInstanceState.getFloat(DIM);
            showBottom = savedInstanceState.getBoolean(BOTTOM);
            outCancel = savedInstanceState.getBoolean(CANCEL);
            animStyle = savedInstanceState.getInt(ANIM);
            layoutId = savedInstanceState.getInt(LAYOUT);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        View view = inflater.inflate(initLayoutId(), container, false);
        convertView(DialogViewHolder.create(view), this);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initParams(); //初始化属性
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //旋转屏幕时可能导致dialog销毁重建的场景发生时，先保存需要的数据，然后在重建时恢复需要的数据，这样就能避免问题了。
        outState.putInt(MARGIN, margin);
        outState.putInt(WIDTH, width);
        outState.putInt(HEIGHT, height);
        outState.putFloat(DIM, dimAmount);
        outState.putBoolean(BOTTOM, showBottom);
        outState.putBoolean(CANCEL, outCancel);
        outState.putInt(ANIM, animStyle);
        outState.putInt(LAYOUT, layoutId);
    }

    /**
     * 配置Dialog的一些属性
     */
    private void initParams() {
        Window window = getDialog().getWindow();
        if (window != null) {

            //设置dialog进入,退出动画
            if (animStyle == 0) {
                animStyle = R.style.DialogFragmentEnterExitAnimation;
            }
            window.setWindowAnimations(animStyle);

            WindowManager.LayoutParams attributes = window.getAttributes();
            //调节灰色背景深浅[0-1] 默认0.5
            attributes.dimAmount = dimAmount;
            //是否在底部显示
            if (showBottom) {
                attributes.gravity = Gravity.BOTTOM;
            }

            //设置dialog宽度
            if (width == 0) {
                //如果用户未设置宽度,则设置一个默认宽度
                attributes.width = SizeUtils.getScreenWidth(getContext()) - 2 * SizeUtils.dp2px
                        (getContext(), margin);
            } else {
                attributes.width = width;
            }

            //设置dialog高度
            if (height == 0) {
                attributes.height = WindowManager.LayoutParams.WRAP_CONTENT;
            } else {
                attributes.height = height;
            }

            //设置LayoutParams 规则
            window.setAttributes(attributes);

        }
        //设置是否可以取消对话框  点击外部的时候
        setCancelable(outCancel);

    }

    /**
     * Dialog灰色背景深浅
     *
     * @param dimAmount Dialog灰色背景深浅 [0-1]  默认0.5
     */
    public BaseNiceDialog setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    /**
     * 是否在底部显示
     *
     * @param showBottom true:显示  false:不显示
     */
    public BaseNiceDialog setShowBottom(boolean showBottom) {
        this.showBottom = showBottom;
        return this;
    }

    /**
     * 设置dialog宽度
     *
     * @param width 宽度
     */
    public BaseNiceDialog setWidth(int width) {
        this.width = width;
        return this;
    }

    /**
     * 设置dialog高度
     *
     * @param height 高度
     */
    public BaseNiceDialog setHeight(int height) {
        this.height = height;
        return this;
    }

    /**
     * 设置距离屏幕左右的margin
     *
     * @param margin 距离屏幕左右的margin
     */
    public BaseNiceDialog setMargin(int margin) {
        this.margin = margin;
        return this;
    }

    /**
     * 设置进入退出动画
     *
     * @param animStyle 进入退出动画
     */
    public BaseNiceDialog setAnimStyle(int animStyle) {
        this.animStyle = animStyle;
        return this;
    }

    /**
     * 设置触摸外部是否取消
     *
     * @param outCancel 触摸外部是否取消
     */
    public BaseNiceDialog setOutCancel(boolean outCancel) {
        this.outCancel = outCancel;
        return this;
    }


}
