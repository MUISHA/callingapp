package com.saifyproduction.callingapp.choise_sos;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

import com.saifyproduction.callingapp.R;

public class ShoisTypeSosFragment extends DialogFragment {
    int position = 0;
    public interface SingleAppelsListSOS{
        void onPositionButtonCliked(String [] list, int position);
        void onPositionNegatifClicked();
    }
    SingleAppelsListSOS mlis;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mlis = (SingleAppelsListSOS) context;
        }catch (Exception exp){
           throw new ClassCastException(getActivity().toString()+ "BIEN SELECTION");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        final String [] list = getActivity().getResources().getStringArray(R.array.TYPE_SOS);
        builder.setTitle("TYPE D'APPELS SOS")
                .setSingleChoiceItems(list, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        position = i;
                    }
                })
                .setPositiveButton("VALIDER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mlis.onPositionButtonCliked(list, position);
                    }
                })
                .setNegativeButton("ANNULER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mlis.onPositionNegatifClicked();
                    }
                });
               return builder.create();
    }
}
