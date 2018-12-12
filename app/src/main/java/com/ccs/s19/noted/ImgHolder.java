package com.ccs.s19.noted;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

class ImgHolder extends RecyclerView.ViewHolder {

    private ImageView img;
    private CheckBox checkBox_pin;
    private TextView textViewLabel;
    private TextView time;

    public ImgHolder(@NonNull View itemView) {
        super(itemView);

        img = itemView.findViewById(R.id.imageView);
        checkBox_pin = itemView.findViewById(R.id.checkBoxPinned);
        textViewLabel = itemView.findViewById(R.id.textViewLabel);
        time = itemView.findViewById(R.id.timeView);

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

    public void setTime(String hour, String minute, String second){
        System.out.println(hour + "Inside set time```");
        time.setText("Time set: " + hour + ":" + minute + ":" + second);
    }

}
