package com.whatshere.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.whatshere.R;
import com.whatshere.custom.CustomFragment;

/**
 * The Class Settings is the fragment that shows the App Settings. You need to
 * write functionality for each Setting option and you can change any of the
 * options as per your need.
 */
public class Settings extends CustomFragment
{

	/* (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.settings, null);

		setTouchNClick(v.findViewById(R.id.lbl1));
		setTouchNClick(v.findViewById(R.id.lbl2));
		setTouchNClick(v.findViewById(R.id.lbl3));
		setTouchNClick(v.findViewById(R.id.lbl4));
		setTouchNClick(v.findViewById(R.id.lbl5));
		return v;
	}

}
