package info.androidhive.listviewfeed.AlbumsTab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.androidhive.listviewfeed.R;

/**
 * Created by Jason on 14/07/2015.
 */
public class Albums extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.albums_layout, container, false);
    return view;
    }
}
