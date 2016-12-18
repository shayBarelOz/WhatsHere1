package com.storiz.ui;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.storiz.PlaceList;
import com.storiz.R;
import com.storiz.custom.CustomFragment;
import com.storiz.model.Data;

/**
 * The Class MainFragment is the base fragment that shows the GridView of
 * various Place categories. You can add your code to do whatever you want
 * related to categories for your app.
 */
public class MainFragment extends CustomFragment
{

	/** The category list. */
	private ArrayList<Data> iList;

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.main_container, null);

		setupView(v);
		return v;
	}

	/* (non-Javadoc)
	 * @see com.whatshere.custom.CustomFragment#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{
		super.onClick(v);
		if (v.getId() == R.id.nearby)
		{
			startActivity(new Intent(getActivity(), PlaceList.class).putExtra(
					"title", getString(R.string.nearby)));
		}
	}

	/**
	 * Setup the view components for this fragment. You write your code for
	 * initializing the views, setting the adapters, touch and click listeners
	 * etc.
	 * 
	 * @param v
	 *            the base view of fragment
	 */
	private void setupView(View v)
	{
		setTouchNClick(v.findViewById(R.id.nearby));
		loadDummyData();
		final GridView grid = (GridView) v.findViewById(R.id.grid);
		grid.setAdapter(new GridAdapter());
		grid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				startActivity(new Intent(getActivity(), PlaceList.class)
						.putExtra("title", iList.get(position).getTexts()[0])
						.putExtra("icon", iList.get(position).getResources()[1])
						.putExtra("icon1",
								iList.get(position).getResources()[2]));
			}
		});
	}

	/**
	 * Load dummy categories data for displaying on the GridView. You need to
	 * write your own code for loading real categories from Web-service or API
	 * and displaying them on GridView.
	 */
	private void loadDummyData()
	{
		iList = new ArrayList<Data>();
		iList.add(new Data(new String[] { "Bars" }, new int[] {
				R.drawable.icon_home_bar, R.drawable.icon_map_beer,
				R.drawable.icon_list_beer }));
		iList.add(new Data(new String[] { "Bank/ATM" }, new int[] {
				R.drawable.icon_home_bank_atm, R.drawable.icon_map_clinic,
				R.drawable.icon_list_bank }));
		iList.add(new Data(new String[] { "Coffee Shops" }, new int[] {
				R.drawable.icon_home_coffee_shop,
				R.drawable.icon_map_bar_copia, R.drawable.icon_list_coffee }));
		iList.add(new Data(new String[] { "Concerts" }, new int[] {
				R.drawable.icon_home_concerts, R.drawable.icon_map_restaurant,
				R.drawable.icon_list_restaurent }));
		iList.add(new Data(new String[] { "Gas Stations" },
				new int[] { R.drawable.icon_home_gas_station, 0,
						R.drawable.icon_list_hotel }));
		iList.add(new Data(new String[] { "Hospitals" }, new int[] {
				R.drawable.icon_home_hospital, R.drawable.icon_map_clinic,
				R.drawable.icon_list_clinic }));
		iList.add(new Data(new String[] { "Local Guides" },
				new int[] { R.drawable.icon_home_local_guide, 0,
						R.drawable.icon_list_hotel }));
		iList.add(new Data(new String[] { "Movie Theaters" }, new int[] {
				R.drawable.icon_home_movie, R.drawable.icon_map_film,
				R.drawable.icon_list_film }));

	}

	/**
	 * The Class GridAdapter is the adapter for grid view used in this fragment.
	 * You must provide valid values for adapter count and must write your code
	 * for binding the data to each item in adapter as per your need.
	 */
	private class GridAdapter extends BaseAdapter
	{
		/* (non-Javadoc)
		 * @see android.widget.Adapter#getCount()
		 */
		@Override
		public int getCount()
		{
			return iList.size();
		}

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getItem(int)
		 */
		@Override
		public Data getItem(int arg0)
		{
			return iList.get(arg0);
		}

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getItemId(int)
		 */
		@Override
		public long getItemId(int arg0)
		{
			return arg0;
		}

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
		 */
		@SuppressLint("InflateParams")
		@Override
		public View getView(int arg0, View v, ViewGroup arg2)
		{
			if (v == null)
				v = getActivity().getLayoutInflater().inflate(
						R.layout.grid_item, null);

			Data d = getItem(arg0);

			TextView lbl = (TextView) v;
			lbl.setText(d.getTexts()[0]);
			lbl.setCompoundDrawablesWithIntrinsicBounds(0, d.getResources()[0],
					0, 0);

			return v;
		}

	}
}
