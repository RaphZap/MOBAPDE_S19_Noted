package com.ccs.s19.noted;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

// Manipulate the view here
public class NoteHolder extends RecyclerView.ViewHolder {

    private TextView text_note;
    private CheckBox checkBox_pin;
    private CheckedTextView checkedTextView_Note;
    private TextView textViewLabel;

    private MainActivity mainActivity;

    public NoteHolder(@NonNull View itemView, MainActivity mainAct) {
        super(itemView);
        mainActivity = mainAct;

//        text_note = itemView.findViewById(R.id.text_note);
        checkBox_pin = itemView.findViewById(R.id.checkBoxPinned);
        checkedTextView_Note = itemView.findViewById(R.id.checkedTextView_Note);
        textViewLabel = itemView.findViewById(R.id.textViewLabel);

        checkBox_pin.setClickable(false);

//        Spinner dropdown = itemView.findViewById(R.id.spinner1);
//        String[] items = new String[]{"1", "2", "three"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(mainActivity , android.R.layout.simple_spinner_dropdown_item, items);
//        dropdown.setAdapter(adapter);
    }

    public void setTextNote(String str){
//        text_note.setText(str);
        checkedTextView_Note.setText(str);
    }

    public void setPin(Boolean pin) {
        checkedTextView_Note.setChecked(pin);
        checkBox_pin.setChecked(pin);
    }

    public void setGroupLabel(String str) {
        textViewLabel.setText(str);
    }
}
