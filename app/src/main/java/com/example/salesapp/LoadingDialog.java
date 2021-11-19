package com.example.salesapp;

import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

public class LoadingDialog {
    Activity activity;
    AlertDialog dialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }
    public  void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.custom_dialog,null));
        builder.setCancelable(true);
        dialog = builder.create();
        dialog.show();
    }
    public void dismissDialog(){
        dialog.dismiss();
    }
}
