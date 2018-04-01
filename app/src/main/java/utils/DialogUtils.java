package utils;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;

/**
 * Created by LFZ on 2017/3/28.
 * Dialog显示
 */

public class DialogUtils {

    private static DialogUtils utils;
    private ProgressDialog progressDialog;
    private OnNegativeClick listener;

    public static DialogUtils getInstance(){
        if (utils == null){
            synchronized (DialogUtils.class){
                if(utils == null){
                    utils = new DialogUtils();
                }
            }
        }
        return utils;
    }


    public void showSimpleAlertDialog(Context context, String title, String message, String positive, String negative){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setTitle(title)
                .setPositiveButton(positive, null)
                .setNegativeButton(negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(listener != null){
                            listener.onClick(dialog,which);
                        }
                    }
                }).create();
        builder.show();
    }

    public void showProgressDialog(Context context, String title, String message){
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        if(!progressDialog.isShowing()){
            progressDialog.show();
        }
    }

    /**
     * 自定义alert
     * @param context
     * @param view
     * @return
     */
    public AlertDialog showCustomedDialog(Context context , View view){
        //方法终于奏效了，用AlertDialogBuilder方法没法通过dismiss或者cancel来关闭
        AlertDialog dialog = new AlertDialog.Builder(context).create();
        dialog.show();
        Window window = dialog.getWindow();
        window.setContentView(view);

        return dialog;
    }

    public void hideProgressDialog(){
        if(getProgressDialog() == null){
            return;
        }
        if(progressDialog.isShowing()){
            progressDialog.dismiss();
        }
    }

    public ProgressDialog getProgressDialog() {
        return progressDialog;
    }

    public interface OnNegativeClick{
        void onClick(DialogInterface dialog, int which);
    }

    public DialogUtils setOnNegativeClickListener(OnNegativeClick listener){
        this.listener = listener;
        return utils;
    }
}
