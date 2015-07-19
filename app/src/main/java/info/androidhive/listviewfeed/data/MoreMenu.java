package info.androidhive.listviewfeed.data;

/**
 * Created by Jason on 13/07/2015.
 */
public class MoreMenu{
    int imageMore;
    String nameMenuMore;
    public MoreMenu(int imageMore, String nameMenuMore) {
        this.imageMore = imageMore;
        this.nameMenuMore = nameMenuMore;
    }

    public int getImageMore() {
        return imageMore;
    }

    public void setImageMore(int imageMore) {
        this.imageMore = imageMore;
    }

    public String getNameMenuMore() {
        return nameMenuMore;
    }

    public void setNameMenuMore(String nameMenuMore) {
        this.nameMenuMore = nameMenuMore;
    }
}
