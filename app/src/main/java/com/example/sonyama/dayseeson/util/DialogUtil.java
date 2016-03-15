package com.example.sonyama.dayseeson.util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.text.TextUtils;

import com.example.sonyama.dayseeson.R;
import com.example.sonyama.dayseeson.data.remote.DayseeError;

import java.util.List;

/**
 * Created by ngoson on 2016/03/16.
 */
public class DialogUtil {

    public static void show(Context context,
                            int resIdTitle,
                            int resIdMesg,
                            final DialogListener dialogListener) {
        DialogUtil.show(context,
                context.getString(resIdTitle),
                context.getString(resIdMesg),
                dialogListener);
    }

    public static void show(Context context,
                            int resIdMesg,
                            final DialogListener dialogListener) {
        DialogUtil.show(context,
                null,
                context.getString(resIdMesg),
                dialogListener);
    }

    public static void show(Context context,
                            String title,
                            String message,
                            final DialogListener dialogListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }
        builder.setCancelable(true);
        builder.setPositiveButton(context.getString(R.string.msg_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogListener != null) dialogListener.onOK();
                        dialog.cancel();
                    }
                });
        builder.setNegativeButton(context.getString(R.string.msg_cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (dialogListener != null) dialogListener.onCancel();
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static void showErrorDialog(Context context, List<DayseeError> errors) {
        StringBuilder errorMessage = new StringBuilder("");
        for (DayseeError error : errors) {
            if (!TextUtils.isEmpty(error.getMessage())) {
                errorMessage.append(error.getMessage() + "\n");
            }
        }
        Resources resources = context.getResources();
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(errorMessage);
        builder.setCancelable(true);
        builder.setPositiveButton(resources.getString(R.string.msg_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}