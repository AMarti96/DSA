package edu.upc.dsa;

public class track extends Dao{

    public String title;
    public String singer;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    @Override
    public String toString() {
        return "track [title=" + title + ", singer=" + singer + "]";
    }

}