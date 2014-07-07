package com.android.meteorid.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.android.meteorid.R;
import com.android.meteorid.dao.City;
import com.android.meteorid.wrapper.CitiesWrapper;

public class ListCitiesAdapter extends BaseAdapter{
	
	// adapter pour list de villes
	
	private LayoutInflater inflater;
	private CitiesWrapper wrapper;
	private ArrayList<City> basicCityList;
	
	public ListCitiesAdapter(Context context, ArrayList<City> list) {
		inflater = LayoutInflater.from(context);
		basicCityList = list;
	}

	@Override
	public int getCount() {
		return basicCityList.size();
	}

	@Override
	public Object getItem(int arg0) {
		return basicCityList.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		return basicCityList.get(position).getId();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		if (row==null) {
			row = inflater.inflate(R.layout.item_city, null);
			wrapper = new CitiesWrapper(row);
			row.setTag(wrapper);			
		}else{
			wrapper = (CitiesWrapper)row.getTag();		
		}

		wrapper.getCityName().setText(basicCityList.get(position).getName());
		
		return(row);
	}
}