package info.androidhive.listviewfeed.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.data.MoreMenu;
import info.androidhive.listviewfeed.widget.CircleImageView;

/**
 * Created by Jason on 13/07/2015.
 */
public class MoreAdapter extends BaseAdapter {
    private ArrayList<Object> moreMenuArray;
    private LayoutInflater inflater;
    private static final int TYPE_MENU = 0;
    private static final int TYPE_HEADER = 1;

    public MoreAdapter(Context context, ArrayList<Object> personArray) {
        this.moreMenuArray = personArray;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return moreMenuArray.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return moreMenuArray.get(position);
    }

    @Override
    public int getViewTypeCount() {
        // TYPE_PERSON and TYPE_DIVIDER
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof MoreMenu) {
            return TYPE_MENU;
        }

        return TYPE_HEADER;
    }

    @Override
    public boolean isEnabled(int position) {
        return (getItemViewType(position) == TYPE_MENU);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        if (convertView == null) {
            switch (type) {
                case TYPE_MENU:
                    convertView = inflater.inflate(R.layout.item_list_more, parent, false);
                    break;
                case TYPE_HEADER:
                    convertView = inflater.inflate(R.layout.row_header_more, parent, false);
                    break;
            }
        }

        switch (type) {

            case TYPE_MENU:
                MoreMenu more = (MoreMenu) getItem(position);
                CircleImageView img = (CircleImageView) convertView.findViewById(R.id.imageListMenu);
                TextView nameUser = (TextView) convertView.findViewById(R.id.txtNameMenuMore);
                img.setImageResource(more.getImageMore());
                nameUser.setText(more.getNameMenuMore());
                break;

            case TYPE_HEADER:
                TextView title = (TextView) convertView.findViewById(R.id.headerTitle);
                String titleString = (String) getItem(position);
                title.setText(titleString);
                break;
        }

        return convertView;
    }
}