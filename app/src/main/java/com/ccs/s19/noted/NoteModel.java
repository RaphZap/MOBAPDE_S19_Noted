package com.ccs.s19.noted;

public class NoteModel {
    private int id;
    private String text;
    private int ImageId;
    private String group;
    private boolean isPinned;

    public NoteModel() {
        this.text = "";
    }

    public NoteModel(int id, String t, int imageid, String grp, boolean isPin) {
        setId(id);
        setText(t);
        setImageId(imageid);
        setGroup(grp);
        setPinned(isPin);
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

    public boolean isPinned() {
        return isPinned;
    }

    public void setPinned(boolean pinned) {
        isPinned = pinned;
    }
}
