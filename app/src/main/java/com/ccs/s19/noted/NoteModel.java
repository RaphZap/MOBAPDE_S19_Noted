package com.ccs.s19.noted;

import java.util.ArrayList;

public class NoteModel {
    public static final int NOTE_TYPE=0;
    public static final int CHECK_TYPE=1;
    public static final int IMG_TYPE=2;

    private int type;
    private int id;
    private String text;
    private ArrayList<String> checkList = new ArrayList<>();
    private int ImageId;
    private String group;
    private boolean isPinned;
    private String hour;
    private String minute;
    private String second;

    public NoteModel(int type, int id, String t, String grp, boolean isPin, String hr, String min, String sec) {
        setType(type);
        setId(id);
        setText(t);
        setGroup(grp);
        setPinned(isPin);
        setHour(hr);
        setMinute(min);
        setSecond(sec);
    }

    public NoteModel(int type, int id, ArrayList<String> t, String grp, boolean isPin) {
        setType(type);
        setId(id);
        setCheckList(t);
        setGroup(grp);
        setPinned(isPin);
    }

    public NoteModel(int type, int id, int imageId, String grp, boolean isPin, String hr, String min, String sec) {
        setType(type);
        setId(id);
        setImageId(imageId);
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

    public ArrayList<String> getCheckList() {
        return checkList;
    }

    public void setCheckList(ArrayList<String> checkList) {
        this.checkList = checkList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
