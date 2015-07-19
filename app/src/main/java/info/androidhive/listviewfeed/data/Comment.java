package info.androidhive.listviewfeed.data;

import java.io.Serializable;

/**
 * Created by Jason on 29/06/2015.
 */
public class Comment implements Serializable {

    private int avata;
    private String name_user, text_comment;

    public int getAvata() {
        return avata;
    }

    public void setAvata(int avata) {
        this.avata = avata;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }

    public String getText_comment() {
        return text_comment;
    }

    public void setText_comment(String text_comment) {
        this.text_comment = text_comment;
    }


}
