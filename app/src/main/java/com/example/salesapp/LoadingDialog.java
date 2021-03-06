package com.example.salesapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;

import androidx.appcompat.app.AlertDialog;

public class LoadingDialog {
    Activity activity;
    AlertDialog dialog;

    public LoadingDialog(Activity activity) {
        this.activity = activity;
    }
    @SuppressLint("ResourceType")
    public  void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
       builder.setView(inflater.inflate(R.layout.custom_dialog, null));
        builder.setCancelable(true);
        dialog = builder.create();
        dialog.setView(inflater.inflate(R.layout.custom_dialog, null));
        dialog.show();
    }
    public void dismissDialog(){
        dialog.dismiss();
    }
}
