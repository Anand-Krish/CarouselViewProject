package info.androidhive.materialdesign.tabsdata;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import info.androidhive.materialdesign.R;


public class OneFragment extends Fragment implements MyPagerAdapter.FragmentTagListener {
    public final static int PAGES = 10;
    // You can choose a bigger number for LOOPS, but you know, nobody will fling
    // more than 1000 times just in order to test your "infinite" ViewPager :D
    public final static int LOOPS = 10;
    public final static int FIRST_PAGE = 9;
    public final static float BIG_SCALE = 1.0f;
    public final static float SMALL_SCALE = 0.7f;//0.8f
    public final static float DIFF_SCALE = BIG_SCALE - SMALL_SCALE;

    public MyPagerAdapter adapter;
    public Myviewpager mypager;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /* setContentView(R.layout.activity_main2); */
        View view = inflater.inflate(R.layout.activity_main2, container, false);
        mypager = (Myviewpager) view.findViewById(R.id.myviewpager);

        adapter = new MyPagerAdapter(getActivity(), getChildFragmentManager(),this,mypager);
        mypager.setAdapter(adapter);
        mypager.setOnPageChangeListener(adapter);

        // Set current item to the middle page so we can fling to both
        // directions left and right
        mypager.setCurrentItem(FIRST_PAGE);

        // Necessary or the pager will only have one extra page to show
        // make this at least however many pages you can see
        mypager.setOffscreenPageLimit(8);

        // Set margin for pages as a negative number, so a part of next and
        // previous pages will be showed

        //
       // pager.setPageMargin(Integer.parseInt(getString(R.string.pagermargin)));Integer.parseInt(getString(R.string.pagermargin))
        mypager.setPageMargin(Integer.parseInt("-340"));
        // Inflate the layout for this fragment
        return view;
    }

    public String getFragmentTag(int position) {
        //pager.getId()
        return "android:switcher:" + R.id.myviewpager + ":" + position;
    }

    @Override
    public String onGetFragmentTag(int position) {
        return "android:switcher:" + R.id.myviewpager + ":" + position;
    }
}
