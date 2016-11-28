package com.whatshere.ui;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.whatshere.PlaceDetail;
import com.whatshere.R;
import com.whatshere.model.Place;

/**
 * The Class Places is a Fragment that is displayed in the PlaceList activity
 * when the user taps on List tab (2nd tab) or when user swipes to Second page
 * in ViewPager. You can customize this fragment's contents as per your need. It
 * just show a list of dummy places with dummy logo for each place.
 */
@SuppressLint("InflateParams")
public class Places extends Fragment
{

	/** The place list. */
	private ArrayList<Place> pList;

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.list, null);

		setupView(v);
		return v;
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
		loadDummyData();
		ListView list = (ListView) v;
		list.setAdapter(new PlaceAdapter());
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id)
			{
				startActivity(new Intent(getActivity(), PlaceDetail.class));
			}
		});
	}

	/**
	 * Load dummy places data for displaying on the Listview. You need to write
	 * your own code for loading real data from Web-service or API and
	 * displaying them on ListView.
	 */
	private void loadDummyData()
	{
		int icon = getActivity().getIntent().getIntExtra("icon1", 0);
		ArrayList<Place> al = new ArrayList<Place>();
		al.add(new Place("Superba Food", "100 m", 3, icon > 0 ? icon
				: R.drawable.icon_list_restaurent));
		al.add(new Place("Coffee Cafe Day", "110 m", 2, icon > 0 ? icon
				: R.drawable.icon_list_coffee));
		al.add(new Place("Bar-be-queue", "120 m", 5, icon > 0 ? icon
				: R.drawable.icon_list_beer));
		al.add(new Place("Om Hospital", "200 m", 1, icon > 0 ? icon
				: R.drawable.icon_list_clinic));
		al.add(new Place("Cinepolice Cinema", "330 m", 0, icon > 0 ? icon
				: R.drawable.icon_list_film));
		al.add(new Place("The World Bank", "350 m", 5, icon > 0 ? icon
				: R.drawable.icon_list_bank));
		al.add(new Place("J-Star Hotel", "410 m", 3, icon > 0 ? icon
				: R.drawable.icon_list_hotel));

		pList = new ArrayList<Place>(al);
		pList.addAll(al);
		pList.addAll(al);
	}

	/**
	 * The Class PlaceAdapter is the adapter for list view used in this fragment.
	 * You must provide valid values for adapter count and must write your code
	 * for binding the data to each item in adapter as per your need.
	 */
	private class PlaceAdapter extends BaseAdapter
	{

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getCount()
		 */
		@Override
		public int getCount()
		{
			return pList.size();
		}

		/* (non-Javadoc)
		 * @see android.widget.Adapter#getItem(int)
		 */
		@Override
		public Place getItem(int arg0)
		{
			return pList.get(arg0);
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
		@Override
		public View getView(int arg0, View v, ViewGroup arg2)
		{
			if (v == null)
				v = getLayoutInflater(null).inflate(R.layout.place_item, null);

			Place d = getItem(arg0);

			TextView lbl = (TextView) v.findViewById(R.id.lbl1);
			lbl.setText(d.getTitle());

			lbl = (TextView) v.findViewById(R.id.lbl2);
			lbl.setText(d.getAddress());

			ImageView img = (ImageView) v.findViewById(R.id.img);
			img.setImageResource(d.getIcon());

			LinearLayout rating = (LinearLayout) v.findViewById(R.id.rating);
			for (int i = 0; i < rating.getChildCount(); i++)
			{
				TextView t = (TextView) rating.getChildAt(i);
				t.setTextColor(getResources().getColor(
						i <= d.getRating() ? R.color.main_red
								: R.color.side_menu_divider));
			}
			return v;
		}

	}
}
