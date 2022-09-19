package com.ashfaq.cse_academic;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Notices {
    private String title, notice, date,sl;

    public Notices(String sl,String title, String notice, String date) {
        this.sl=sl;
        this.title = title;
        this.notice = notice;
        this.date = date;
    }
    public Notices()
    {

    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
