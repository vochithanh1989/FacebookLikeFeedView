package info.androidhive.listviewfeed.AlbumsTab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.adapter.ItemsAdapter;

/**
 * Created by Jason on 14/07/2015.
 */
public class Photos extends Fragment {
    private ListView listViewLeft;
    private ListView listViewRight;
    private ItemsAdapter leftAdapter;
    private ItemsAdapter rightAdapter;

    int[] leftViewsHeights;
    int[] rightViewsHeights;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.photos_layout, container, false);
        listViewLeft = (ListView) view.findViewById(R.id.list_view_left);
        listViewRight = (ListView) view.findViewById(R.id.list_view_right);

        loadItems();

        listViewLeft.setOnTouchListener(touchListener);
        listViewRight.setOnTouchListener(touchListener);
        listViewLeft.setOnScrollListener(scrollListener);
        listViewRight.setOnScrollListener(scrollListener);
        return view;
    }

    // Passing the touch event to the opposite list
    View.OnTouchListener touchListener = new View.OnTouchListener() {
        boolean dispatched = false;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (v.equals(listViewLeft) && !dispatched) {
                dispatched = true;
                listViewRight.dispatchTouchEvent(event);
            } else if (v.equals(listViewRight) && !dispatched) {
                dispatched = true;
                listViewLeft.dispatchTouchEvent(event);
            }

            dispatched = false;
            return false;
        }
    };

    /**
     * Synchronizing scrolling
     * Distance from the top of the first visible element opposite list:
     * sum_heights(opposite invisible screens) - sum_heights(invisible screens) + distance from top of the first visible child
     */
    AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {

        @Override
        public void onScrollStateChanged(AbsListView v, int scrollState) {
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {

            if (view.getChildAt(0) != null) {
                if (view.equals(listViewLeft) ){
                    leftViewsHeights[view.getFirstVisiblePosition()] = view.getChildAt(0).getHeight();

                    int h = 0;
                    for (int i = 0; i < listViewRight.getFirstVisiblePosition(); i++) {
                        h += rightViewsHeights[i];
                    }

                    int hi = 0;
                    for (int i = 0; i < listViewLeft.getFirstVisiblePosition(); i++) {
                        hi += leftViewsHeights[i];
                    }

                    int top = h - hi + view.getChildAt(0).getTop();
                    listViewRight.setSelectionFromTop(listViewRight.getFirstVisiblePosition(), top);
                } else if (view.equals(listViewRight)) {
                    rightViewsHeights[view.getFirstVisiblePosition()] = view.getChildAt(0).getHeight();

                    int h = 0;
                    for (int i = 0; i < listViewLeft.getFirstVisiblePosition(); i++) {
                        h += leftViewsHeights[i];
                    }

                    int hi = 0;
                    for (int i = 0; i < listViewRight.getFirstVisiblePosition(); i++) {
                        hi += rightViewsHeights[i];
                    }

                    int top = h - hi + view.getChildAt(0).getTop();
                    listViewLeft.setSelectionFromTop(listViewLeft.getFirstVisiblePosition(), top);
                }

            }

        }
    };

    private void loadItems(){
        Integer[] leftItems = new Integer[]{R.drawable.pic1, R.drawable.pic2, R.drawable.ic_3, R.drawable.ic_4, R.drawable.ic_5};
        Integer[] rightItems = new Integer[]{R.drawable.ic_6, R.drawable.ic_7, R.drawable.ic_8, R.drawable.ic_9, R.drawable.ic_10};

        leftAdapter = new ItemsAdapter(getActivity(), R.layout.albums_item, leftItems);
        rightAdapter = new ItemsAdapter(getActivity(), R.layout.albums_item, rightItems);
        listViewLeft.setAdapter(leftAdapter);
        listViewRight.setAdapter(rightAdapter);

        leftViewsHeights = new int[leftItems.length];
        rightViewsHeights = new int[rightItems.length];
    }

}
