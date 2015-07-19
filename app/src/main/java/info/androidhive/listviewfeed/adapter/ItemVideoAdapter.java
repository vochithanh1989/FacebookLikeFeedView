package info.androidhive.listviewfeed.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Html;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

import info.androidhive.listviewfeed.CommentActivity;
import info.androidhive.listviewfeed.PopupMenu.ActionItem;
import info.androidhive.listviewfeed.PopupMenu.QuickAction;
import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.app.AppController;
import info.androidhive.listviewfeed.data.FeedItem;

public class ItemVideoAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<FeedItem> feedItems;

    private static final int ID_SHARE = 1;
    private static final int ID_WRITE = 2;
    private static final int ID_SEEND = 3;

    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public ItemVideoAdapter(Activity activity, List<FeedItem> feedItems) {
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        ActionItem sharetItem = new ActionItem(ID_SHARE, "Share Now", activity.getResources().getDrawable(R.drawable.share));
        ActionItem writeItem = new ActionItem(ID_WRITE, "Write Post \n ", activity.getResources().getDrawable(R.drawable.ic_write));
        ActionItem messengerItem = new ActionItem(ID_SEEND, "Send as Message ", activity.getResources().getDrawable(R.drawable.messenger));

        //use setSticky(true) to disable QuickAction dialog being dismissed after an item is clicked
        sharetItem.setSticky(true);
        writeItem.setSticky(true);

        //create QuickAction. Use QuickAction.VERTICAL or QuickAction.HORIZONTAL param to define layout
        //orientation
        final QuickAction quickAction = new QuickAction(this.activity, QuickAction.VERTICAL);

        //add action items into QuickAction
        quickAction.addActionItem(sharetItem);
        quickAction.addActionItem(writeItem);
        quickAction.addActionItem(messengerItem);


        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.item_profile, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();

        TextView name = (TextView) convertView.findViewById(R.id.video_name);
        TextView timestamp = (TextView) convertView
                .findViewById(R.id.video_timestamp);
        TextView statusMsg = (TextView) convertView
                .findViewById(R.id.video_txtStatusMsg);
        TextView url = (TextView) convertView.findViewById(R.id.video_txtUrl);
        NetworkImageView profilePic = (NetworkImageView) convertView
                .findViewById(R.id.video_profilePic);
        final VideoView myVideo = (VideoView) convertView
                .findViewById(R.id.myVideo);

        final FeedItem item = feedItems.get(position);

        name.setText(item.getName());

        // Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(item.getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        timestamp.setText(timeAgo);


        // Chcek for empty status message
        if (!TextUtils.isEmpty(item.getStatus())) {
            statusMsg.setText(item.getStatus());
            statusMsg.setVisibility(View.VISIBLE);
        } else {
            // status is empty, remove from view
            statusMsg.setVisibility(View.GONE);
        }

        // Checking for null feed url
        if (item.getUrl() != null) {
            url.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
                    + item.getUrl() + "</a> "));

            // Making url clickable
            url.setMovementMethod(LinkMovementMethod.getInstance());
            url.setVisibility(View.VISIBLE);
        } else {
            // url is null, remove from the view
            url.setVisibility(View.GONE);
        }

        // user profile pic
        profilePic.setImageUrl(item.getProfilePic(), imageLoader);
        myVideo.setVideoPath("/sdcard/Download/big_buck_bunny.mp4");
        MediaController controller = new MediaController(activity);
        myVideo.setMediaController(controller);
        controller.setAnchorView(myVideo);
        final ImageButton imb_play = (ImageButton) convertView.findViewById(R.id.imb_play);
        imb_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myVideo.start();
                imb_play.setVisibility(View.GONE);
            }
        });

//        // Feed image
//        if (item.getImge() != null) {
//            feedImageView.setImageUrl(item.getImge(), imageLoader);
//            feedImageView.setVisibility(View.VISIBLE);
//            feedImageView
//                    .setResponseObserver(new FeedImageView.ResponseObserver() {
//                        @Override
//                        public void onError() {
//                        }
//
//                        @Override
//                        public void onSuccess() {
//                        }
//                    });
//        } else {
//            feedImageView.setVisibility(View.GONE);
//        }

        final TextView like = (TextView) convertView.findViewById(R.id.video_btn_like);
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setSelected(!v.isSelected());
                if (v.isSelected()) {
                    like.setText("Thích");
                    item.setCountLike(item.getCountLike() + 1);
                    item.setLiked(true);
                    like.setTextColor(Color.parseColor("#5890ff"));
                    like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.likeon, 0, 0, 0);
                    notifyDataSetChanged();
                } else {
                    like.setText("Thích");
                    item.setCountLike(item.getCountLike() + -1);
                    item.setLiked(false);
                    like.setTextColor(Color.parseColor("#000000"));
                    like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like, 0, 0, 0);
                    notifyDataSetChanged();

                }
            }
        });
        if (item.isLiked()) {
            like.setText("Thích");
            like.setTextColor(Color.parseColor("#5890ff"));
            like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.likeon, 0, 0, 0);
        } else {
            like.setText("Thích");
            like.setTextColor(Color.parseColor("#000000"));
            like.setCompoundDrawablesWithIntrinsicBounds(R.drawable.like, 0, 0, 0);
        }
        TextView countlike = (TextView) convertView.findViewById(R.id.video_txtCountLike);
        if (item.getCountLike() == 0) {
            countlike.setVisibility(View.GONE);
        } else {
            countlike.setText(item.getCountLike() + " người thích");
            countlike.setVisibility(View.VISIBLE);
        }
        //------Button Comment-----------
        TextView btn_comment = (TextView) convertView.findViewById(R.id.video_btn_comment);
        btn_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, CommentActivity.class);
                FeedItem item = feedItems.get(position);
                intent.putExtra("Item", item);
                activity.startActivity(intent);

            }
        });
        //-----Button Share---------

        TextView btn_share = (TextView) convertView.findViewById(R.id.video_btn_share);
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  quickAction.show(v);
                Dialog dialog = new Dialog(v.getContext());
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.menu_share);
                dialog.show();
            }
        });
        //--------------------------
        return convertView;
    }


}
