package com.gerus.linio.views;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.gerus.linio.R;


/**
 * Created by gerus-mac on 04/02/18.
 */

public class Dialogs {

    private AlertDialog mProgressDialog;
    private Activity mActivity;
    private DialogInterface.OnDismissListener onDismissListener;

    public Dialogs(Activity poContext) {
        this.onDismissListener = null;
        mActivity = poContext;
    }

    public Dialogs(Activity poContext, DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        mActivity = poContext;
    }

    public void showProgressBar() {
        showProgressBar(false);
    }

    public void showProgressBar(boolean isCancelable) {

        final AlertDialog.Builder mAlertDialog = new AlertDialog.Builder(mActivity);
        View mView = mActivity.getLayoutInflater().inflate(R.layout.dialog_progress, null);
        mAlertDialog.setView(mView);
        mAlertDialog.setCancelable((isCancelable));
        mProgressDialog = mAlertDialog.show();
        mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        mProgressDialog.setOnDismissListener(onDismissListener);
    }

    public void dismissProgressBar() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

}
