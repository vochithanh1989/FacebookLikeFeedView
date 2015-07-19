package info.androidhive.listviewfeed.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.app.AppController;
import info.androidhive.listviewfeed.app.FormatDateTime;
import info.androidhive.listviewfeed.data.FeedItem;

public class MessengerAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<FeedItem> feedItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public MessengerAdapter(Activity activity, List<FeedItem> feedItems) {
        this.activity = activity;
        this.feedItems = feedItems;
    }

    @Override
    public int getCount() {
        return feedItems.size();
    }

    @Override
    public Object getItem(int location) {
        return feedItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.messenger_item, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        TextView name = (TextView) convertView.findViewById(R.id.txtUserNameMessenger);
        TextView lastDate = (TextView) convertView.findViewById(R.id.txtDateTime);

        NetworkImageView imgavatar = (NetworkImageView) convertView
                .findViewById(R.id.imageAvatarMessenger);
        final FeedItem item = feedItems.get(position);

        name.setText(item.getName());
        lastDate.setText(FormatDateTime.CurrentDate());


        // user profile pic
        imgavatar.setImageUrl(item.getProfilePic(), imageLoader);

        return convertView;
    }

}
