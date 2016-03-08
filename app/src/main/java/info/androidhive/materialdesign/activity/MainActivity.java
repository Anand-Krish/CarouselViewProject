package info.androidhive.materialdesign.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import info.androidhive.materialdesign.R;
import info.androidhive.materialdesign.tabsdata.MessageDialogFragment;

public class MainActivity extends AppCompatActivity implements FragmentDrawer.FragmentDrawerListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private Toolbar mToolbar;
    private FragmentDrawer drawerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        // display the first navigation drawer view on app launch
        displayView(0);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.notification) {
            Toast.makeText(getApplicationContext(), "Notification Selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.location) {
            Toast.makeText(getApplicationContext(), "Location Selected", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.module) {
            Toast.makeText(getApplicationContext(), "Module list Selected", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                fragment=new GalleryFragment();
                title="Card Gallery";
                break;
            case 1:
                //Preferences
                fragment = new PreferenceFragment();
                title = getString(R.string.title_preferences);
                break;
            case 2:
                // My spends
                fragment = new MySpendsFragment();
                title = getString(R.string.title_my_spends);
                break;

            case 3:
                //Scan my invoices
                fragment = new ChangePaswordFragment();
                title = "Change password";
                break;

            case 4:
                //Scan my invoices
                fragment = new ScanInvoicesFragment();
                title = getString(R.string.title_scan_my_invoices);
                break;

            case 5:
                //Learn more
                fragment = new LearnMoreFragment();
                title = getString(R.string.title_learnmore);
                break;

            case 6:
                //Contact help desk
                fragment = new ContactHelpdeskFragment();
                title = getString(R.string.title_contact_helpdesk);
                break;

            case 7:
                //Logout
                finish();
                break;

        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }
    /**
     * Method to show Message Dialog for given text
     *
     * @author akrishnan
     * @date Dec-23-2015
     * @param Id
     *            to show the Content from Resource like R.String.app_name
     *
     */

    public void showMessageDialog(int Id) {
        if (mdf == null || mdf.getDialog() == null
                || !mdf.getDialog().isShowing()) {
            mdf = MessageDialogFragment.newInstance(Id);
            mdf.show(getSupportFragmentManager(), "messagedialogfragment");
        }
    }
    public MessageDialogFragment mdf;
}