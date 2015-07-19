package info.androidhive.listviewfeed.Tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import info.androidhive.listviewfeed.ProfileActivity;
import info.androidhive.listviewfeed.R;
import info.androidhive.listviewfeed.adapter.MoreAdapter;
import info.androidhive.listviewfeed.data.MoreMenu;

/**
 * Created by jason on 18/06/2015.
 */
public class More extends Fragment {
    private View mHeader;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.more_layout, container, false);

        ListView listView = (ListView) view.findViewById(R.id.listMenuMore);
        //Header Listview
        mHeader = inflater.inflate(R.layout.header_more, null);
        listView.addHeaderView(mHeader);

//        LinearLayout click =(LinearLayout) mHeader.findViewById(R.id.clickprofile);
        mHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfileActivity.class);
                startActivity(intent); }
        });


        MoreMenu friends = new MoreMenu(R.drawable.friends, "Friends");
        MoreMenu instagram = new MoreMenu(R.drawable.stagram, "Instagram");
        MoreMenu games = new MoreMenu(R.drawable.pic1, "Games");
        MoreMenu onthisday = new MoreMenu(R.drawable.onthisday, "On This Day");
        MoreMenu chat = new MoreMenu(R.drawable.chat, "Chat");
        MoreMenu likepage = new MoreMenu(R.drawable.likepage, "Like Pages");
        MoreMenu nearby = new MoreMenu(R.drawable.nearby, "Nearby Places");
        MoreMenu football = new MoreMenu(R.drawable.pic1, "Top Eleven Be a Football Manager");
        MoreMenu apps = new MoreMenu(R.drawable.apps, "Apps for you");
        MoreMenu events = new MoreMenu(R.drawable.pic2, "Events");

        MoreMenu most = new MoreMenu(R.drawable.most, "Most Recent");
        MoreMenu close = new MoreMenu(R.drawable.most, "Close Friends");
        MoreMenu family = new MoreMenu(R.drawable.most, "Family");

        MoreMenu beta = new MoreMenu(R.drawable.beta, "Beta profram");
        MoreMenu appsettings = new MoreMenu(R.drawable.terms, "App Settings");
        MoreMenu newsfeed = new MoreMenu(R.drawable.about, "News Feed Preferences");
        MoreMenu account = new MoreMenu(R.drawable.account, "Account Settings");
        MoreMenu code = new MoreMenu(R.drawable.code, "Code Generator");
        MoreMenu help = new MoreMenu(R.drawable.help, "Help Center");
        MoreMenu activity = new MoreMenu(R.drawable.activity, "Activity Log");
        MoreMenu privacy = new MoreMenu(R.drawable.privacy, "Pravacy Shortcuts");
        MoreMenu terms = new MoreMenu(R.drawable.terms, "Terms&Policies");
        MoreMenu report = new MoreMenu(R.drawable.activity, "Report a Problem");
        MoreMenu about = new MoreMenu(R.drawable.about, "About");
        MoreMenu mobile = new MoreMenu(R.drawable.mobi, "Mobile Data");
        MoreMenu logout = new MoreMenu(R.drawable.logout, "Log Out");

        ArrayList<Object> menu = new ArrayList<>();
        menu.add("Favorites");
        menu.add(friends);
        menu.add(instagram);

        menu.add("Apps");
        menu.add(games);
        menu.add(onthisday);
        menu.add(chat);
        menu.add(likepage);
        menu.add(nearby);
        menu.add(football);
        menu.add(apps);
        menu.add(events);

        menu.add("Feeds");
        menu.add(most);
        menu.add(close);
        menu.add(family);

        menu.add("Settings");
        menu.add(beta);
        menu.add(appsettings);
        menu.add(newsfeed);
        menu.add(account);
        menu.add(code);
        menu.add(help);
        menu.add(activity);
        menu.add(privacy);

        menu.add(terms);
        menu.add(report);
        menu.add(about);
        menu.add(mobile);
        menu.add(logout);

        listView.setAdapter(new MoreAdapter(getActivity(), menu));
        return view;
    }
}
