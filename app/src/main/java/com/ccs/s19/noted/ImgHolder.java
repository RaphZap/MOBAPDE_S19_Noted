package com.ccs.s19.noted;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

class ImgHolder extends RecyclerView.ViewHolder {

    private ImageView img;
    private CheckBox checkBox_pin;
    private TextView textViewLabel;

    public ImgHolder(@NonNull View itemView) {
        super(itemView);

        img = itemView.findViewById(R.id.imageView);
        checkBox_pin = itemView.findViewById(R.id.checkBoxPinned);
        textViewLabel = itemView.findViewById(R.id.textViewLabel);

        checkBox_pin.setClickable(false);
    }

    public void setPin(Boolean pin) {
        checkBox_pin.setChecked(pin);
    }

    public void setGroupLabel(String str) {
        textViewLabel.setText(str);
    }

    public void setImg(String imageId) {
        img.setImageBitmap(StringToBitMap(imageId));
    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
