package com.ccs.s19.noted;

public class NoteModel {
    private int id;
    private String text;
    private int ImageId;
    private String group;
    private boolean isPinned;
    private String hour;
    private String minute;
    private String second;

    public NoteModel() {
        this.text = "";
    }

    public NoteModel(int id, String t, int imageid, String grp, boolean isPin, String hr, String min, String sec) {
        setId(id);
        setText(t);
        setImageId(imageid);
        setGroup(grp);
        setPinned(isPin);
        setHour(hr);
        setMinute(min);
        setSecond(sec);
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

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second;
    }
}
