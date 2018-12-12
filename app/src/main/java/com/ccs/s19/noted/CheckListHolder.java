package com.ccs.s19.noted;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.TableLayout;
import android.widget.TextView;

class CheckListHolder extends RecyclerView.ViewHolder{

    private CheckBox checkBox_pin;
    private TableLayout main_table;
    private TextView textViewLabel;

    public CheckListHolder(@NonNull View itemView) {
        super(itemView);

        checkBox_pin = itemView.findViewById(R.id.checkBoxPinned);
        main_table = itemView.findViewById(R.id.main_table);
        textViewLabel = itemView.findViewById(R.id.textViewLabel);

        checkBox_pin.setClickable(false);
    }

    public void setPin(Boolean pin) {
        checkBox_pin.setChecked(pin);
    }

    public void setGroupLabel(String str) {
        textViewLabel.setText(str);
    }



}
