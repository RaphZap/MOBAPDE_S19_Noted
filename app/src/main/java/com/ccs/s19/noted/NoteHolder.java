package com.ccs.s19.noted;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.ToggleButton;

// Manipulate the view here
public class NoteHolder extends RecyclerView.ViewHolder {

    private TextView text_note;
    private CheckBox checkBox_pin;

    public NoteHolder(@NonNull View itemView) {
        super(itemView);

        text_note = itemView.findViewById(R.id.text_note);
        checkBox_pin = itemView.findViewById(R.id.checkBoxPinned);
    }

    public void setTextNote(String str){
        text_note.setText(str);
    }
}
