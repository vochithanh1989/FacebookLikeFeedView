package info.androidhive.listviewfeed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.data.Comment;


public class CommentAdapter extends ArrayAdapter<Comment> {

    Context mContext;
    ArrayList<Comment> mList = new ArrayList<Comment>();


    public CommentAdapter(Context context, int resource, ArrayList<Comment> objects) {
        super(context, resource, objects);

        this.mContext = context;
        // TODO Auto-generated constructor stub
        this.mList = objects;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Comment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {

        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder mViewHolder;

        if (convertView == null) {
            LayoutInflater inflate = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflate.inflate(R.layout.item_list_comment, null);

            mViewHolder = new ViewHolder();

            mViewHolder.avata = (ImageView) convertView.findViewById(R.id.avata);
            mViewHolder.name = (TextView) convertView.findViewById(R.id.txtnameuser);
            mViewHolder.text_comment = (TextView) convertView.findViewById(R.id.txttextcomment);


            convertView.setTag(mViewHolder);
        } else {

            mViewHolder = (ViewHolder) convertView.getTag();

        }
        Comment comment = mList.get(position);
        mViewHolder.name.setText(comment.getName_user());
        mViewHolder.avata.setImageResource(comment.getAvata());
        mViewHolder.text_comment.setText(comment.getText_comment());
        return convertView;

    }

    static class ViewHolder {

        TextView name;
        ImageView avata;
        TextView text_comment;

    }


}

