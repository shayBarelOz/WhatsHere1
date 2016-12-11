package com.whatshere.ui;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
//import android.R.id;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.whatshere.Manifest;
import com.whatshere.PlaceDetail;
import com.whatshere.R;
import com.whatshere.custom.CustomFragment;
import com.whatshere.model.Place;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * The Class MapViewer is the fragment that shows the Google Map. You
 * can add your code to do whatever you want related to Map functions for your
 * app. For example you can add Map markers here or can show places on map.
 */
public class MapViewer extends CustomFragment implements OnClickListener
{

	/** The map view. */
	private MapView mMapView;

	/** The Google map. */
	public GoogleMap mMap;

	/** The place list. */
	private ArrayList<Place> pList;

    private MapFragment mapFragment;


    /* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View v = inflater.inflate(R.layout.map, null);

		loadDummyData();
		initMap(v, savedInstanceState);

		return v;
	}

	/**
	 * Initialize the Map view.
	 * 
	 * @param v
	 *            the v
	 * @param savedInstanceState
	 *            the saved instance state object passed from OnCreateView
	 *            method of fragment.
	 */
	private void initMap(View v, Bundle savedInstanceState)
	{
		try
		{

            
            //// FIXME: 27/11/2016  need to fix this as http://stackoverflow.com/questions/15249683/adding-google-maps-api-v2-to-an-existing-project
//			mapFragment = ((MapFragment)getFragmentManager().findFragmentById(R.id.map));
//            mMap = mapFragment.getMap();
//            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
//            mMap.setMyLocationEnabled(false);

			MapsInitializer.initialize(getActivity());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		mMapView = (MapView) v.findViewById(R.id.map);
		mMapView.onCreate(savedInstanceState);
	}

	/**
	 * This method can be used to show the markers on the map. Current
	 * implementation of this method will show only a few dummy Pins with title and
	 * snippet. You must customize this method to show the pins as per your
	 * need.
	 */
	private void setupMapMarkers()
	{
		mMap.clear();
		LatLng l[] = { new LatLng(45.4667, 9.1833),
				new LatLng(45.4868, 9.1034), new LatLng(45.4467, 9.11331),
				new LatLng(45.42671, 9.1633) };
		for (Place p : pList)
		{
			MarkerOptions opt = new MarkerOptions();
			opt.position(p.getGeo()).title(p.getTitle())
					.snippet(p.getAddress());
			opt.icon(BitmapDescriptorFactory.fromResource(p.getIcon()));

			mMap.addMarker(opt);
		}
		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(l[2], 11));
	}

	/**
	 * Load dummy places data for displaying on the Map View. You need to write
	 * your own code for loading real data from
	 * Web-service or API and displaying them on Map View.
	 */
	private void loadDummyData()
	{
		int icon = getActivity().getIntent().getIntExtra("icon", 0);
		pList = new ArrayList<Place>();
		pList.add(new Place("Superba Food",
				"1900 S Lincoln Blvd, Venice\nLos Angeles", new LatLng(45.4667,
						9.1833), icon > 0 ? icon
						: R.drawable.icon_map_restaurant));
		pList.add(new Place("Coffee Cafe Day",
				"1234 A Lincoln Blvd, Venice\nLos Angeles", new LatLng(45.4868,
						9.1034), icon > 0 ? icon
						: R.drawable.icon_map_bar_copia));
		pList.add(new Place("Bar-be-queue", "C-395, A-One Mall\nSydney",
				new LatLng(45.42671, 9.1633), icon > 0 ? icon
						: R.drawable.icon_map_beer));
		pList.add(new Place("Om Hospital", "Om Hospital road\nSydney",
				new LatLng(45.4467, 9.11331), icon > 0 ? icon
						: R.drawable.icon_map_clinic));
		pList.add(new Place("Cinepolice Cinema",
				"1900 S Lincoln Blvd, Venice\nLos Angeles", new LatLng(45.4367,
						9.1833), icon > 0 ? icon : R.drawable.icon_map_film));
	}

	/**
	 * This class creates a Custom a InfoWindowAdapter that is used to show
	 * popup on map when user taps on a pin on the map. Current implementation
	 * of this class will show a Title and a snippet with one static image.
	 * 
	 */
	private class CustomInfoWindowAdapter implements InfoWindowAdapter
	{

		/** The contents view. */
		private final View mContents;

		/**
		 * Instantiates a new custom info window adapter.
		 */
		@SuppressLint("InflateParams")
		CustomInfoWindowAdapter()
		{

			mContents = getActivity().getLayoutInflater().inflate(
					R.layout.map_popup, null);
		}

		/* (non-Javadoc)
		 * @see com.google.android.gms.maps.GoogleMap.InfoWindowAdapter#getInfoWindow(com.google.android.gms.maps.model.Marker)
		 */
		@Override
		public View getInfoWindow(Marker marker)
		{
			render(marker, mContents);
			return mContents;
		}

		/* (non-Javadoc)
		 * @see com.google.android.gms.maps.GoogleMap.InfoWindowAdapter#getInfoContents(com.google.android.gms.maps.model.Marker)
		 */
		@Override
		public View getInfoContents(Marker marker)
		{

			return null;
		}

		/**
		 * Render the marker content on Popup view. Customize this as per your
		 * need.
		 * 
		 * @param marker
		 *            the marker
		 * @param view
		 *            the content view
		 */
		private void render(final Marker marker, View view)
		{

			String title = marker.getTitle();
			TextView titleUi = (TextView) view.findViewById(R.id.title);
			if (title != null)
			{
				SpannableString titleText = new SpannableString(title);
				titleText.setSpan(new ForegroundColorSpan(Color.WHITE), 0,
						titleText.length(), 0);
				titleUi.setText(titleText);
			}
			else
			{
				titleUi.setText("");
			}

			String snippet = marker.getSnippet();
			TextView snippetUi = (TextView) view.findViewById(R.id.snippet);
			if (snippet != null)
			{
				SpannableString snippetText = new SpannableString(snippet);
				snippetText.setSpan(new ForegroundColorSpan(getResources()
						.getColor(R.color.main_blue_lt)), 0, snippet.length(),
						0);
				snippetUi.setText(snippetText);
			}
			else
			{
				snippetUi.setText("");
			}

		}
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onResume()
	 */


	//@Override
//	public void onMapReady(final GoogleMap map) {
//		this.mMap = map;
//		mMap.setMyLocationEnabled(true);
//	}

	@Override
	public void onResume()
	{

		// FIXME: 28/11/2016 do this according to map fregmanet
//		MapFragment mapFragment = (MapFragment) getFragmentManager()
//				.findFragmentById(R.id.map);
//		//mapFragment.getMapAsync(onMapReady(R.id.map));
		//mMap = mMapView.getMap();


		super.onResume();
		mMapView.onResume();


//		if (mMap == null) {
//            // Try to obtain the map from the SupportMapFragment.
//           // mMap = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map))
//            //        .getMap();
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                    == PackageManager.PERMISSION_GRANTED) {
//                mMap.setMyLocationEnabled(true);
//            } else {
//                Toast.makeText(MapsActivity.this, "You have to accept to enjoy all app's services!", Toast.LENGTH_LONG).show();
//                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                        == PackageManager.PERMISSION_GRANTED) {
//                    mMap.setMyLocationEnabled(true);
//                }
//            }
//        }

		//mMap = mMapView.getMap();
//		if (mMap != null)
//		{
//		//	mMap.setMyLocationEnabled(true);
//			mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
//			setupMapMarkers();
//			mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
//
//				@Override
//				public void onInfoWindowClick(Marker arg0)
//				{
//					startActivity(new Intent(getActivity(), PlaceDetail.class));
//				}
//			});
//		}
		if (mMap != null)
		{
			//mMap.setMyLocationEnabled(true);
			mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
			setupMapMarkers();
			mMap.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {

				@Override
				public void onInfoWindowClick(Marker arg0)
				{
					startActivity(new Intent(getActivity(), PlaceDetail.class));
				}
			});
		}
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onPause()
	 */
	@Override
	public void onPause()
	{

		mMapView.onPause();
		if (mMap != null)
			mMap.setInfoWindowAdapter(null);
		super.onPause();
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onDestroy()
	 */
	@Override
	public void onDestroy()
	{
		mMapView.onDestroy();
		super.onDestroy();
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onLowMemory()
	 */
	@Override
	public void onLowMemory()
	{
		super.onLowMemory();
		mMapView.onLowMemory();
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onSaveInstanceState(android.os.Bundle)
	 */
	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		super.onSaveInstanceState(outState);
		mMapView.onSaveInstanceState(outState);
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v)
	{

	}

}
