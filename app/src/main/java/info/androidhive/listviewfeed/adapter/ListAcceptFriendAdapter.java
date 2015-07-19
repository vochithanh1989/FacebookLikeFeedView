package info.androidhive.listviewfeed.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.app.AppController;
import info.androidhive.listviewfeed.data.FeedItem;

public class ListAcceptFriendAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<FeedItem> feedItems;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ListAcceptFriendAdapter(Activity activity, List<FeedItem> feedItems) {
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
            convertView = inflater.inflate(R.layout.item_list_add_friend, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        TextView name = (TextView) convertView.findViewById(R.id.txtName);
        NetworkImageView imgavatar = (NetworkImageView) convertView
                .findViewById(R.id.imgavatar);
        final FeedItem item = feedItems.get(position);

        name.setText(item.getName());


        // user profile pic
        imgavatar.setImageUrl(item.getProfilePic(), imageLoader);

        final Button btn_add=(Button) convertView.findViewById(R.id.btnChapNhan);
        final Button btn_delete=(Button) convertView.findViewById(R.id.btnXoa);
        final Button btn_banbe=(Button) convertView.findViewById(R.id.btnBanBe);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               btn_add.setVisibility(View.GONE);
                btn_delete.setVisibility(View.GONE);
                btn_banbe.setVisibility(View.VISIBLE);
            }
        });

        return convertView;
    }

}
