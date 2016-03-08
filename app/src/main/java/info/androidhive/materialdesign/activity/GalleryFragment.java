package info.androidhive.materialdesign.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.tabsdata.OneFragment;

/**
 * Created by akrishnan on 17-12-2015.
 */
public class GalleryFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;

    public GalleryFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_simple_tabs, container, false);

        FloatingActionsMenu topMenu = (FloatingActionsMenu) rootView.findViewById(R.id.multiple_actions_top);
        FloatingActionsMenu leftMenu = (FloatingActionsMenu) rootView.findViewById(R.id.multiple_actions_left);
        FloatingActionButton fab_add_card=(FloatingActionButton)rootView.findViewById(R.id.add_card);
        FloatingActionButton lock_card=(FloatingActionButton)rootView.findViewById(R.id.lockcard);
        //FloatingActionsMenu downMenu = (FloatingActionsMenu) findViewById(R.id.multiple_actions_down1);
        topMenu.addFABMenu(leftMenu);
        viewPager = (ViewPager) rootView.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        viewPager.setOffscreenPageLimit(8);
        tabLayout = (TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        fab_add_card.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), dummyActivity.class);
                startActivity(i);
            }
        });
        lock_card.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).showMessageDialog(R.string.drawer_open);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new OneFragment(), "PAYMENT CARDS");
        adapter.addFragment(new OneFragment(), "LOYALTY CARDS");
        //  adapter.addFragment(new ThreeFragment(), "Loyalty");
        viewPager.setAdapter(adapter);
    }

    public String getFragmentTag(int position) {
        if (adapter != null) {
            Fragment page = getChildFragmentManager().findFragmentByTag("android:switcher:" + R.id.viewpager + ":" + viewPager.getCurrentItem());
            if (page instanceof OneFragment) {
                return ((OneFragment) page).getFragmentTag(position);
            }
        }
        //  return "android:switcher:" + context.pager.getId() + ":" + position;
        return null;
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
