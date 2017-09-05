# NiceDialog

一个基于DialogFragment的对话框,扩展性超好.而且使用很简单.

可以通过DialogViewConvertListener接口,方便得控制UI

 NiceDialog.init().setLayoutId(R.layout.layout_my_dialog)
                        .setConvertListener(new DialogViewConvertListener() {
                            @Override
                            public void convertView(DialogViewHolder viewHolder, BaseNiceDialog
                                    dialog) {
                                //在这里进行View相关的操作
                                Random random = new Random();
                                viewHolder.setText(R.id.btn_ok,random.nextInt(100)+"");
                            }
                        }).setDimAmount(0.3f)
                        .setWidth(400)
                        .setHeight(200)
                        .setOutCancel(true)
                        .setAnimStyle(R.style.DialogFragmentEnterExitAnimation)
                        .show(getSupportFragmentManager(), "test");