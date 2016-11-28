package com.whatshere;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.whatshere.custom.CustomActivity;
import com.whatshere.ui.MapViewer;
import com.whatshere.ui.Places;

/**
 * The PlaceList is the activity class that shows Map fragment and List
 * Fragment. This activity shows Places listing with two option to view places
 * on Map or in List. You need to write actual logic for displaying real place
 * listing and locations. Both List and Map are added in ViewPager to provide
 * Sliding navigation.
 */
public class PlaceList extends CustomActivity
{

	/** The search view. */
	private SearchView searchView;

	/** The current tab. */
	private View currentTab;

	/** The pager. */
	private ViewPager pager;

	/* (non-Javadoc)
	 * @see com.food.custom.CustomActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place_list);

		getActionBar().setTitle(getIntent().getStringExtra("title"));
		initTabs();
		initPager();

	}

	/* (non-Javadoc)
	 * @see com.activity.custom.CustomActivity#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{
		super.onClick(v);
		if (v.getId() == R.id.tab1)
			pager.setCurrentItem(0, true);
		else if (v.getId() == R.id.tab2)
			pager.setCurrentItem(1, true);
	}

	/**
	 * Initialize the tabs. You can write your code related to Tabs.
	 */
	private void initTabs()
	{
		findViewById(R.id.tab1).setOnClickListener(this);
		findViewById(R.id.tab2).setOnClickListener(this);
		setCurrentTab(0);
	}

	/**
	 * Sets the current selected tab. Called whenever a Tab is selected either
	 * by clicking on Tab button or by swiping the ViewPager. You can write your
	 * code related to tab selection actions.
	 * 
	 * @param page
	 *            the current page of ViewPager
	 */
	private void setCurrentTab(int page)
	{
		if (currentTab != null)
			currentTab.setEnabled(true);
		if (page == 0)
			currentTab = findViewById(R.id.tab1);
		else if (page == 1)
			currentTab = findViewById(R.id.tab2);
		currentTab.setEnabled(false);

	}

	/**
	 * Initialize the ViewPager. You can customize this method for writing the
	 * code related to view pager actions.
	 */
	private void initPager()
	{
		pager = (ViewPager) findViewById(R.id.pager);
		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int page)
			{
				setCurrentTab(page);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2)
			{
			}

			@Override
			public void onPageScrollStateChanged(int arg0)
			{
			}
		});
		pager.setAdapter(new DummyPageAdapter(getSupportFragmentManager()));
	}

	/**
	 * The Class DummyPageAdapter is a dummy pager adapter for ViewPager. You
	 * can customize this adapter as per your needs.
	 */
	private class DummyPageAdapter extends FragmentPagerAdapter
	{

		/**
		 * Instantiates a new dummy page adapter.
		 * 
		 * @param fm
		 *            the FragmentManager
		 */
		public DummyPageAdapter(FragmentManager fm)
		{
			super(fm);
		}

		/* (non-Javadoc)
		 * @see android.support.v4.app.FragmentPagerAdapter#getItem(int)
		 */
		@Override
		public Fragment getItem(int pos)
		{
			if (pos == 0)
				return new MapViewer();
			if (pos == 1)
				return new Places();
			return null;
		}

		/* (non-Javadoc)
		 * @see android.support.v4.view.PagerAdapter#getCount()
		 */
		@Override
		public int getCount()
		{
			return 2;
		}

	}

	/* (non-Javadoc)
	 * @see com.newsfeeder.custom.CustomActivity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.search_exp, menu);
		if (getActionBar().getTitle() == getString(R.string.nearby))
			menu.findItem(R.id.menu_loc).setVisible(false);

		setupSearchView(menu);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.activity.custom.CustomActivity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if (item.getItemId() == R.id.menu_loc)
		{
			startActivity(new Intent(this, PlaceList.class).putExtra("title",
					getString(R.string.nearby)));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Setup the up search view for ActionBar search. The current implementation
	 * simply search for Videos from Youtube. You can customize this code to
	 * search from your API or any TV API.
	 * 
	 * @param menu
	 *            the ActionBar Menu
	 */
	protected void setupSearchView(Menu menu)
	{
		searchView = (SearchView) menu.findItem(R.id.menu_search)
				.getActionView();
		searchView.setIconifiedByDefault(true);
		searchView.setQueryHint("Search...");
		searchView.requestFocusFromTouch();
		searchView.setOnQueryTextListener(new OnQueryTextListener() {

			@Override
			public boolean onQueryTextSubmit(String query)
			{
				return true;
			}

			@Override
			public boolean onQueryTextChange(String query)
			{
				return false;
			}
		});

		setupSearchViewTheme(searchView);
	}

	/**
	 * Sets the up search view theme.
	 * 
	 * @param searchView
	 *            the new up search view theme
	 */
	protected void setupSearchViewTheme(SearchView searchView)
	{
		int id = searchView.getContext().getResources()
				.getIdentifier("android:id/search_src_text", null, null);
		EditText searchPlate = (EditText) searchView.findViewById(id);
		searchPlate.setHintTextColor(getResources().getColor(R.color.white));

		id = searchView.getContext().getResources()
				.getIdentifier("android:id/search_close_btn", null, null);
		((ImageView) searchView.findViewById(id))
				.setImageResource(R.drawable.ic_close);

		id = searchView.getContext().getResources()
				.getIdentifier("android:id/search_mag_icon", null, null);
		((ImageView) searchView.findViewById(id))
				.setImageResource(R.drawable.ic_search_small);

		id = searchView.getContext().getResources()
				.getIdentifier("android:id/search_plate", null, null);
		searchView.findViewById(id).setBackgroundResource(
				R.drawable.edittext_bg_white);
		try
		{
			id = searchView.getContext().getResources()
					.getIdentifier("android:id/search_button", null, null);
			((ImageView) searchView.findViewById(id))
					.setImageResource(R.drawable.icon_search);
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
