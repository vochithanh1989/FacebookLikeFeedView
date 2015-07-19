package info.androidhive.listviewfeed.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import info.androidhive.listviewfeed.Tab.Messenger;
import info.androidhive.listviewfeed.Tab.More;
import info.androidhive.listviewfeed.Tab.News;
import info.androidhive.listviewfeed.Tab.Notification;
import info.androidhive.listviewfeed.Tab.Request;

/**
 * Created by Jason 18/06/2015.
 */
public class ViewpagerAdapter extends FragmentStatePagerAdapter  {
    int[] icons;

    public ViewpagerAdapter(FragmentManager fm,int[] icons) {
        super(fm);
        this.icons=icons;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new News();
            case 1:
                return new Request();
            case 2:
                return new Messenger();
            case 3:
                return new Notification();
            case 4:
                return new More();
        }
        return null;
    }

    @Override
    public int getCount() {
        return icons.length;
    }

//    @Override
//    public CharSequence getPageTitle(int position) {
//        Drawable image= context.getResources().getDrawable(titles[position]);
//        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
//        SpannableString sb = new SpannableString(" ");
//        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
//        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        return sb;
//    }
    public int getDrawableId(int positon){
        return icons[positon];
    }
}
