package com.storiz.model;

import com.google.android.gms.maps.model.LatLng;

/**
 * The Class Place is java bean class that hold all the properties for a Place.
 */
public class Place
{

	/** The title. */
	private String title;

	/** The address. */
	private String address;

	/** The icon. */
	private int icon;

	/** The rating. */
	private int rating;

	/** The geo. */
	private LatLng geo;

	/**
	 * Instantiates a new place.
	 * 
	 * @param title
	 *            the title
	 * @param address
	 *            the address
	 * @param geo
	 *            the geo
	 * @param icon
	 *            the icon
	 */
	public Place(String title, String address, LatLng geo, int icon)
	{
		this.title = title;
		this.address = address;
		this.icon = icon;
		this.geo = geo;
	}

	/**
	 * Instantiates a new place.
	 * 
	 * @param title
	 *            the title
	 * @param address
	 *            the address
	 * @param rating
	 *            the rating
	 * @param icon
	 *            the icon
	 */
	public Place(String title, String address, int rating, int icon)
	{
		this.title = title;
		this.address = address;
		this.icon = icon;
		this.rating = rating;
	}

	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the new title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * Gets the address.
	 * 
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}

	/**
	 * Sets the address.
	 * 
	 * @param address
	 *            the new address
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}

	/**
	 * Gets the icon.
	 * 
	 * @return the icon
	 */
	public int getIcon()
	{
		return icon;
	}

	/**
	 * Sets the icon.
	 * 
	 * @param icon
	 *            the new icon
	 */
	public void setIcon(int icon)
	{
		this.icon = icon;
	}

	/**
	 * Gets the geo.
	 * 
	 * @return the geo
	 */
	public LatLng getGeo()
	{
		return geo;
	}

	/**
	 * Sets the geo.
	 * 
	 * @param geo
	 *            the new geo
	 */
	public void setGeo(LatLng geo)
	{
		this.geo = geo;
	}

	/**
	 * Gets the rating.
	 * 
	 * @return the rating
	 */
	public int getRating()
	{
		return rating;
	}

	/**
	 * Sets the rating.
	 * 
	 * @param rating
	 *            the new rating
	 */
	public void setRating(int rating)
	{
		this.rating = rating;
	}

}
