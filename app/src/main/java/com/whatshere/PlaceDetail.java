package com.whatshere;

import android.os.Bundle;

import com.whatshere.custom.CustomActivity;

/**
 * The PlaceDetail is the activity class that shows details about a selected
 * Place. This activity only shows dummy detail text and Image, you need to load
 * and display actual contents.
 */
public class PlaceDetail extends CustomActivity
{

	/* (non-Javadoc)
	 * @see com.food.custom.CustomActivity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.place_details);

		getActionBar().setTitle("Superba Food");

	}

}
