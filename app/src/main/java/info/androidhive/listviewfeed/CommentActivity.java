package info.androidhive.listviewfeed;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;

import info.androidhive.listviewfeed.adapter.CommentAdapter;
import info.androidhive.listviewfeed.app.AppController;
import info.androidhive.listviewfeed.data.Comment;
import info.androidhive.listviewfeed.data.FeedItem;

/**
 * Created by Jason on 26/06/2015.
 */
public class CommentActivity extends Activity {
    ArrayList<Comment> mList = new ArrayList<Comment>();
    private View mHeader;
    ListView mlistview;
    CommentAdapter madapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        mlistview = (ListView) findViewById(R.id.lvcomment);

        mHeader = getLayoutInflater().inflate(R.layout.header_comment, null);
        mlistview.addHeaderView(mHeader);

        madapter = new CommentAdapter(this, R.layout.item_list_comment, mList);
        mlistview.setAdapter(madapter);
        final ImageView add = (ImageView) findViewById(R.id.btnAddComment);
        final TextView textview_comment = (TextView) findViewById(R.id.edtComment);



        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3b5998")));

        textview_comment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                add.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

        add.setOnClickListener(new View.OnClickListener()

                               {
                                   @Override
                                   public void onClick(View view) {
                                       String txtComment = textview_comment.getText().toString();
                                       Comment comment = new Comment();
                                       comment.setAvata(R.drawable.avatar4);
                                       comment.setName_user("Doremon (^'^)");
                                       comment.setText_comment(txtComment);
                                       mList.add(comment);
                                       textview_comment.setText("");
                                       add.setVisibility(View.GONE);
                                       madapter.notifyDataSetChanged();

                                   }
                               }

        );

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        TextView name = (TextView) mHeader.findViewById(R.id.name_comment);
        TextView timestamp = (TextView) mHeader.findViewById(R.id.timestamp_comment);
        TextView statusMsg = (TextView) mHeader.findViewById(R.id.txtStatusMsg_comment);
        TextView url = (TextView) mHeader.findViewById(R.id.txtUrl_comment);
        NetworkImageView profilePic = (NetworkImageView) mHeader.findViewById(R.id.profilePic_comment);
        FeedImageView feedImageView = (FeedImageView) mHeader.findViewById(R.id.feedImage_comment);

        Intent intent = getIntent();
        FeedItem item = (FeedItem) intent.getSerializableExtra("Item");
        name.setText(item.getName());

        // Converting timestamp into x ago format
        CharSequence timeAgo = DateUtils.getRelativeTimeSpanString(
                Long.parseLong(item.getTimeStamp()),
                System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS);
        timestamp.setText(timeAgo);


        // Chcek for empty status message
        if (!TextUtils.isEmpty(item.getStatus()))

        {
            statusMsg.setText(item.getStatus());
            statusMsg.setVisibility(View.VISIBLE);
        } else

        {
            // status is empty, remove from view
            statusMsg.setVisibility(View.GONE);
        }

        // Checking for null feed url
        if (item.getUrl() != null)

        {
            url.setText(Html.fromHtml("<a href=\"" + item.getUrl() + "\">"
                    + item.getUrl() + "</a> "));

            // Making url clickable
            url.setMovementMethod(LinkMovementMethod.getInstance());
            url.setVisibility(View.VISIBLE);
        } else

        {
            // url is null, remove from the view
            url.setVisibility(View.GONE);
        }

        // user profile pic
        profilePic.setImageUrl(item.getProfilePic(), imageLoader);

        // Feed image
        if (item.getImge() != null)

        {
            feedImageView.setImageUrl(item.getImge(), imageLoader);
            feedImageView.setVisibility(View.VISIBLE);
            feedImageView
                    .setResponseObserver(new FeedImageView.ResponseObserver() {
                        @Override
                        public void onError() {
                        }

                        @Override
                        public void onSuccess() {
                        }
                    });
        } else

        {
            feedImageView.setVisibility(View.GONE);
        }

    }
}
