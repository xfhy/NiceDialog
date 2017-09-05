package com.xfhy.nicedialog.widget;

import java.io.Serializable;

/**
 * author feiyang
 * create at 2017/9/5 17:55
 * description：Dialog的回调接口
 * <p>
 * 由于我们在NiceDialog类中有一个ViewConvertListener的接口回调，如果用NiceDialog
 * 创建对话框，在屏幕发生旋转时会导致设置的ViewConvertListener实例被释放掉，所以之前给View绑定的点击事件、View
 * 的属性配置就会失效，恢复到初始状态，所以就需要保存、恢复ViewConvertListener的实例，我的做法是让接口实现Serializable，
 * 这样就能通过Bundle来保存了
 */
public interface DialogViewConvertListener extends Serializable {

    long serialVersionUID = System.currentTimeMillis();

    void convertView(DialogViewHolder viewHolder, BaseNiceDialog dialog);

}
