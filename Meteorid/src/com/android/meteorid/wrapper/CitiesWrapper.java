package com.android.meteorid.wrapper;

import com.android.meteorid.R;

import android.view.View;
import android.widget.TextView;

public class CitiesWrapper {
	
	private View baseView;
	private TextView cityName;

	public CitiesWrapper(View base) {
		this.baseView = base;
	}  

	public View getBaseView(){
		return baseView;
	}

	public TextView getCityName(){
		if (cityName == null) {
			cityName = (TextView)baseView.findViewById(R.id.city_name);
		}
		return(cityName);
	}
}