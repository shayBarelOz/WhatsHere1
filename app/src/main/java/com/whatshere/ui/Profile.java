package com.whatshere.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.whatshere.R;
import com.whatshere.custom.CustomFragment;

/**
 * The Class Profile is the fragment that shows the User Profile. You can add
 * your code to do whatever you want related to user profile information for
 * your app. For example you can load and display actual image of user.
 */
@SuppressLint("InflateParams")
public class Profile extends CustomFragment
{
	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.profile, null);

		return v;
	}

}
