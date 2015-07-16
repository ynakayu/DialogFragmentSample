package com.example.dialogsample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by ynakayu on 15/07/16.
 */
public class SampleDialogFragment extends DialogFragment{
    public static SampleDialogFragment newInstance() {
        return new SampleDialogFragment();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("test message")

        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                Intent result = new Intent();
                if (getTargetFragment() != null) {
                    // If the caller is in Fragment.
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, result);
                } else {
                    // If the caller is in Activity.
                    PendingIntent pi = getActivity().createPendingResult(getTargetRequestCode(), result, PendingIntent.FLAG_ONE_SHOT);
                    try {
                        pi.send(Activity.RESULT_OK);
                    } catch (PendingIntent.CanceledException ex) {
                        ex.printStackTrace();
                    }

                }

            }

        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Intent result = new Intent();
                if (getTargetFragment() != null) {
                    // If the caller is in Fragment.
                    getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED, result);
                } else {
                    // If the caller is in Activity.
                    PendingIntent pi = getActivity().createPendingResult(getTargetRequestCode(), result, PendingIntent.FLAG_ONE_SHOT);
                    try {
                        pi.send(Activity.RESULT_CANCELED);
                    } catch (PendingIntent.CanceledException ex) {
                        ex.printStackTrace();
                    }

                }

            }

        });

        return builder.create();
    }

}
