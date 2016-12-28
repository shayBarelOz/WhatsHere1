package com.storiz.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.storiz.R;
import com.storiz.model.Data;

import java.util.ArrayList;

/**
 * The Adapter class for the ListView displayed in the left navigation drawer.
 */
public class LeftNavAdapter extends BaseAdapter
{

	/** The items. */
	private ArrayList<Data> items;

	/** The context. */
	private Context context;

	/** The selection. */
	private int selection;

	/**
	 * Checks if is selection.
	 *
	 * @return the int
	 */
	public int isSelection()
	{
		return selection;
	}

	/**
	 * Sets the selection.
	 *
	 * @param selection the new selection
	 */
	public void setSelection(int selection)
	{
		this.selection = selection;
		notifyDataSetChanged();
	}

	/**
	 * Instantiates a new left navigation adapter.
	 * 
	 * @param context
	 *            the context of activity
	 * @param items
	 *            the array of items to be displayed on ListView
	 */
	public LeftNavAdapter(Context context, ArrayList<Data> items)
	{
		this.context = context;
		this.items = items;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getCount()
	 */
	@Override
	public int getCount()
	{
		return items.size();
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Data getItem(int arg0)
	{
		return items.get(arg0);
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position)
	{
		return position;
	}

	/* (non-Javadoc)
	 * @see android.widget.Adapter#getView(int, android.view.View, android.view.ViewGroup)
	 */
	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		if (convertView == null)
			convertView = LayoutInflater.from(context).inflate(
					R.layout.left_nav_item, null);

		Data f = getItem(position);
		TextView lbl = (TextView) convertView.findViewById(R.id.lbl);
		lbl.setText(f.getTexts()[0]);

		// set the color of element that we are choosing to be red.
		if (selection == position)
		{
			lbl.setTextColor(context.getResources().getColor(R.color.main_red));
			lbl.setCompoundDrawablesWithIntrinsicBounds(f.getResources()[1], 0,
					0, 0);
			convertView.findViewById(R.id.bar).setVisibility(View.VISIBLE);
		}
		else

		// if we didn't choose it stay white.
		{
			lbl.setTextColor(Color.WHITE);
			lbl.setCompoundDrawablesWithIntrinsicBounds(f.getResources()[0], 0,
					0, 0);
			convertView.findViewById(R.id.bar).setVisibility(View.INVISIBLE);
		}

		return convertView;
	}

}
