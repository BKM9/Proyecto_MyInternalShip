package com.example.internalship.utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class Alertas {

    public interface ConfirmationListener {
        void onConfirmed();
        void onCanceled();
    }

    public static void showConfirmationDialog(Context context, String title, String message, final ConfirmationListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(message)
                .setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (listener != null) {
                            listener.onConfirmed();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if (listener != null) {
                            listener.onCanceled();
                        }
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
