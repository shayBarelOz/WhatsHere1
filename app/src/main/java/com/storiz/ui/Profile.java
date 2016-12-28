package com.storiz.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.storiz.R;
import com.storiz.custom.CustomFragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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

	View v;
	LayoutInflater inflater1;
	ViewGroup container1;



	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}



	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode,resultCode,data);
	}
	@Override
	public void onResume(){
		super.onResume();
	}



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{


		//inflater1 = inflater;
		//container1 = container;

		v = inflater.inflate(R.layout.profile, container, false);
		Intent intent = getActivity().getIntent();
		Bundle params = getArguments();
		if (params!=null && intent!=null ) {
			((TextView) v.findViewById(R.id.EmailText))
					.setText(params.getString("email"));
			((TextView) v.findViewById(R.id.NameText))
					.setText(params.getString("name"));
			((TextView) v.findViewById(R.id.headlineBirthdate))
					.setText(params.getString("birthday"));
			((TextView) v.findViewById(R.id.headlineEmail))
					.setText(params.getString("email"));
			((TextView) v.findViewById(R.id.headlineName))
					.setText(params.getString("name"));
			((TextView) v.findViewById(R.id.SexText))
					.setText(params.getString("gender"));
			((TextView) v.findViewById(R.id.BirthDayText))
					.setText(params.getString("birthday"));

			// this inidicate that the view will be changed in the future ! very important
			v.invalidate();
			try {
				//Handler h = new Handler();
                //calling to the http connection must be async !

			drawableFromUrl url = new drawableFromUrl();
			url.execute(params.getString("picture"));
			}catch (Exception e){
				e.printStackTrace();

		}
		}

		return v;
	}




	public class drawableFromUrl extends AsyncTask<String,Void,Drawable>
	{

//		protected void onPreExecute() {
//			//display progress dialog.
//		}
		protected Drawable doInBackground(String... url)  {
			Bitmap x;

			try {

				HttpURLConnection connection = (HttpURLConnection) new URL(url[0]).openConnection();
				connection.connect();
				InputStream input = connection.getInputStream();

				x = BitmapFactory.decodeStream(input);
				return new BitmapDrawable(x);

			}catch (IOException e){
				e.printStackTrace();
			}
			return null;
		}
		protected void onPostExecute(Drawable result) {
			if (result!=null){

				Intent intent = getActivity().getIntent();
				Bundle params = getArguments();

				if (params!=null && intent!=null ) {


				//	v = inflater1.inflate(R.layout.profile, container1, false);
					v.findViewById(R.id.picViewBackground).setBackground(result);
					//((TextView)v.findViewById(R.id.picView)).setCompoundDrawablesWithIntrinsicBounds(null,result,null,null);

					//fixme:  fix the design of the picture
					((TextView)v.findViewById(R.id.picView)).setCompoundDrawables(null,result,null,null);

					//vi.invalidate();

				//	vi.findViewById(R.id.picView).setBackground(result);


//					((TextView) v.findViewById(R.id.EmailText))
//							.setText(params.getString("email"));
//					((TextView) v.findViewById(R.id.NameText))
//							.setText(params.getString("name"));
//					((TextView) v.findViewById(R.id.headlineBirthdate))
//							.setText(params.getString("birthday"));
//					((TextView) v.findViewById(R.id.headlineEmail))
//							.setText(params.getString("email"));
//					((TextView) v.findViewById(R.id.headlineName))
//							.setText(params.getString("name"));
//					((TextView) v.findViewById(R.id.SexText))
//							.setText(params.getString("gender"));
//					((TextView) v.findViewById(R.id.BirthDayText))
//							.setText(params.getString("birthday"));
					//return new SampleView(this);

				}

			}
		}
			// dismiss progress dialog and update ui}
	}

}
