package com.xfhy.nicedialog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.xfhy.nicedialog.widget.BaseNiceDialog;
import com.xfhy.nicedialog.widget.DialogViewConvertListener;
import com.xfhy.nicedialog.widget.DialogViewHolder;
import com.xfhy.nicedialog.widget.NiceDialog;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mShowInputDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mShowInputDialog = (Button) findViewById(R.id.btn_show_dialog);
        mShowInputDialog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_show_dialog:
                NiceDialog.init().setLayoutId(R.layout.layout_my_dialog)
                        .setConvertListener(listener).setDimAmount(0.3f)
                        .setWidth(400)
                        .setHeight(300)
                        .setOutCancel(true)
                        .setAnimStyle(R.style.DialogFragmentEnterExitAnimation)
                        .show(getSupportFragmentManager(), "test");
                break;
        }
    }

    private static DialogViewConvertListener listener = new DialogViewConvertListener() {
        @Override
        public void convertView(DialogViewHolder viewHolder, BaseNiceDialog dialog) {
            //在这里进行View相关的操作
            Random random = new Random();
            viewHolder.setText(R.id.btn_ok, random.nextInt(100) + "");
        }
    };
}
