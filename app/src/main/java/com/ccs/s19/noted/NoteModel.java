package com.ccs.s19.noted;

public class NoteModel {
    private int id;
    private String text;
    private int ImageId;
    private String group;

    public NoteModel() {
        this.text = "";
    }

    public NoteModel(String t) {
        setText(t);
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
