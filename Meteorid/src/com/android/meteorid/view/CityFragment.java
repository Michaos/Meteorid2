package com.android.meteorid.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.meteorid.R;
import com.android.meteorid.dao.City;

public class CityFragment extends Fragment{
	
	// ce fragment est appeler par CityPagerActivity
	
	// affiche les infos de la ville das le header 
	// + un viewpager avec les temperatures < aujourdhui  -  semaine >
	// via fragment TemperaturesFragment
	
	private static City myCity;
	
	public static CityFragment newInstance(City city) {
		Bundle bundle = new Bundle();
		bundle.putSerializable("city", city);
		CityFragment frag = new CityFragment();
		frag.setArguments(bundle);
		return frag;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_city, container, false);
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		myCity = (City) getArguments().getSerializable("city");
		System.out.println(myCity.getName());
		
		((TextView) view.findViewById(R.id.city)).setText(myCity.getName());
		((TextView) view.findViewById(R.id.temp)).setText(myCity.getDayList().get(0).getTempDay()+"°C");
		((TextView) view.findViewById(R.id.description)).setText(myCity.getDayList().get(0).getDescription());
		((TextView) view.findViewById(R.id.morning)).setText("Matin\n"+myCity.getDayList().get(0).getTempMorning()+"°C");
		((TextView) view.findViewById(R.id.day)).setText("Midi\n"+myCity.getDayList().get(0).getTempDay()+"°C");
		((TextView) view.findViewById(R.id.evening)).setText("Soir\n"+myCity.getDayList().get(0).getTempEvening()+"°C");
		((TextView) view.findViewById(R.id.nuit)).setText("Nuit\n"+myCity.getDayList().get(0).getTempNight()+"°C");
		
		String description = myCity.getDayList().get(0).getDescription();
		
		if(description.equalsIgnoreCase("")){
			((RelativeLayout) view.findViewById(R.id.header)).setBackgroundResource(R.drawable.background_brouillard);
		}else if (description.equalsIgnoreCase("")){
			((RelativeLayout) view.findViewById(R.id.header)).setBackgroundResource(R.drawable.background_lune_nuage);
		}else if (description.equalsIgnoreCase("")){
			((RelativeLayout) view.findViewById(R.id.header)).setBackgroundResource(R.drawable.background_lune);
		}else if (description.equalsIgnoreCase("")){
			((RelativeLayout) view.findViewById(R.id.header)).setBackgroundResource(R.drawable.background_neige);
		}else if (description.equalsIgnoreCase("couvert")){
			((RelativeLayout) view.findViewById(R.id.header)).setBackgroundResource(R.drawable.background_nuageux);
		}else if (description.equalsIgnoreCase("pluies modérées") || description.equalsIgnoreCase("légères pluies")){
			((RelativeLayout) view.findViewById(R.id.header)).setBackgroundResource(R.drawable.background_pluvieux);
		}else if (description.equalsIgnoreCase("partiellement ensoleillé") || description.equalsIgnoreCase("peu nuageux")){
			((RelativeLayout) view.findViewById(R.id.header)).setBackgroundResource(R.drawable.background_soleil_nuage);
		}else if (description.equalsIgnoreCase("ensoleillé")){
			((RelativeLayout) view.findViewById(R.id.header)).setBackgroundResource(R.drawable.background_soleil);
		}else if (description.equalsIgnoreCase("fortes pluies")){
			((RelativeLayout) view.findViewById(R.id.header)).setBackgroundResource(R.drawable.background_tempete);
		}
		
		super.onViewCreated(view, savedInstanceState);
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);
	}
	
}