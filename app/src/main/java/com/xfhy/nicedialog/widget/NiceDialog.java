package com.xfhy.nicedialog.widget;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

/**
 * author feiyang
 * create at 2017/9/5 17:47
 * description：
 * 提供了设置dialog布局文件以及绑定接口的方
 */
public class NiceDialog extends BaseNiceDialog {

    private static final String DIALOG_LISTENER_KEY = "listener";
    /**
     * 外界可以通过该listener进行UI控制
     */
    private DialogViewConvertListener listener;

    public static NiceDialog init() {
        return new NiceDialog();
    }

    @Override
    public int initLayoutId() {
        return layoutId;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            listener = (DialogViewConvertListener) savedInstanceState.get(DIALOG_LISTENER_KEY);
        }
    }

    @Override
    public void convertView(DialogViewHolder viewHolder, BaseNiceDialog dialog) {
        if (listener != null) {
            listener.convertView(viewHolder, dialog);
        }
    }

    public NiceDialog setLayoutId(@LayoutRes int layoutId) {
        this.layoutId = layoutId;
        return this;
    }

    /**
     * 设置一个listener,可以方便得进行UI操作
     *
     * @param convertListener DialogViewConvertListener
     */
    public NiceDialog setConvertListener(DialogViewConvertListener convertListener) {
        this.listener = convertListener;
        return this;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(DIALOG_LISTENER_KEY, listener);
    }
}
