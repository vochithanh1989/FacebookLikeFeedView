package info.androidhive.listviewfeed;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import info.androidhive.listviewfeed.SlidingTab.SlidingTabAlbumLayout;
import info.androidhive.listviewfeed.adapter.ViewPagerAlbumAdapter;

public class AlbumActivity extends FragmentActivity {
    ViewPager viewPager;
    SlidingTabAlbumLayout tabs;
    ViewPagerAlbumAdapter pagerAdapter;
    CharSequence Titles[]={"PHOTOS OF YOU","UPLOAD","ALBUM","SYNCED"};
    int Numboftabs =4;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.album_layout);

        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#3b5998")));
        this.setTitle("Võ Chí Thành");


        pagerAdapter =  new ViewPagerAlbumAdapter(getSupportFragmentManager(),Titles,Numboftabs);
        // Assigning ViewPager View and setting the adapter
        viewPager = (ViewPager) findViewById(R.id.viewPagerAlbum);
        viewPager.setAdapter(pagerAdapter);
        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabAlbumLayout) findViewById(R.id.tabAlbums);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabAlbumLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.link);
            }
        });
        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(viewPager);
    }

}
