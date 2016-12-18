package com.storiz;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.storiz.custom.CustomActivity;
import com.storiz.custom.CustomFragment;
import com.storiz.model.Data;
import com.storiz.ui.LeftNavAdapter;
import com.storiz.ui.MainFragment;
import com.storiz.ui.Profile;
import com.storiz.ui.Settings;

/**
 * The Activity MainActivity will launched after the Login and it is the
 * Home/Base activity of the app which holds all the Fragments and also show the
 * Sliding Navigation drawer. You can write your code for displaying actual
 * items on Drawer layout.
 */
public class MainActivity extends CustomActivity
{

	/** The drawer layout. */
	private DrawerLayout drawerLayout;

	/** ListView for left side drawer. */
	private ListView drawerLeft;

	/** The drawer toggle. */
	private ActionBarDrawerToggle drawerToggle;



	/* (non-Javadoc)
	 * @see com.newsfeeder.custom.CustomActivity#onCreate(android.os.Bundle)
	 */
	@Override

	/**
	 * This function will be called once login button will be pressed
	 */
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setupDrawer();
		setupContainer(1);
	}

	/**
	 * Setup the drawer layout. This method also includes the method calls for
	 * setting up the Left & Right side drawers.
	 */
	private void setupDrawer()
	{
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			@Override
			public void onDrawerClosed(View view)
			{
			}

			@Override
			public void onDrawerOpened(View drawerView)
			{
			}
		};
		drawerLayout.setDrawerListener(drawerToggle);
		drawerLayout.closeDrawers();

		setupLeftNavDrawer();
	}

	/**
	 * Setup the left navigation drawer/slider. You can add your logic to load
	 * the contents to be displayed on the left side drawer. It will also setup
	 * the Header and Footer contents of left drawer. This method also apply the
	 * Theme for components of Left drawer.
	 */
	@SuppressLint("InflateParams")
	private void setupLeftNavDrawer()
	{
		drawerLeft = (ListView) findViewById(R.id.left_drawer);

		View header = getLayoutInflater().inflate(R.layout.left_nav_header,
				null);

		drawerLeft.addHeaderView(header);

		final ArrayList<Data> al = new ArrayList<Data>();
		al.add(new Data(new String[] { "Find" }, new int[] {
				R.drawable.ic_nav1, R.drawable.ic_nav1_sel }));
		al.add(new Data(new String[] { "Favorite" }, new int[] {
				R.drawable.ic_nav2, R.drawable.ic_nav2_sel }));
		al.add(new Data(new String[] { "Settings" }, new int[] {
				R.drawable.ic_nav3, R.drawable.ic_nav3_sel }));
		al.add(new Data(new String[] { "Log Out" }, new int[] {
				R.drawable.ic_nav4, R.drawable.ic_nav4_sel }));

		final LeftNavAdapter adp = new LeftNavAdapter(this, al);
		drawerLeft.setAdapter(adp);
		drawerLeft.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> l, View arg1, int pos,
					long arg3)
			{
				if (pos != 2)
					adp.setSelection(pos - 1);
				drawerLayout.closeDrawers();
				setupContainer(pos);
			}
		});

	}

	/**
	 * Setup the container fragment for drawer layout. This method will setup
	 * the grid view display of main contents. You can customize this method as
	 * per your need to display specific content.
	 * 
	 * @param pos
	 *            the new up container
	 */
	private void setupContainer(int pos)
	{
		if (pos == 4)
		{
			startActivity(new Intent(this, Login.class));
			finish();
		}
		CustomFragment f = null;
		String title = getString(R.string.app_name);

		if (pos == 0)
		{
			f = new Profile();
			title = "Anna Mccoy";
		}
		else if (pos == 1)
			f = new MainFragment();
		else if (pos == 2)
		{
			startActivity(new Intent(this, PlaceList.class).putExtra("title",
					"Favorites"));
		}
		else if (pos == 3)
		{
			f = new Settings();
			title = "Settings";
		}
		if (f == null)
			return;

		getActionBar().setTitle(title);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.content_frame, f).commit();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onPostCreate(android.os.Bundle)
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState)
	{
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		drawerToggle.syncState();
	}

	/* (non-Javadoc)
	 * @see android.app.Activity#onConfigurationChanged(android.content.res.Configuration)
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig)
	{
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggle
		drawerToggle.onConfigurationChanged(newConfig);
	}

	/* (non-Javadoc)
	 * @see com.whatshere.custom.CustomActivity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (drawerToggle.onOptionsItemSelected(item))
		{
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
