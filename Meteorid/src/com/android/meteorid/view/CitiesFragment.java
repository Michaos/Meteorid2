package com.android.meteorid.view;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.android.meteorid.R;
import com.android.meteorid.adapter.ListCitiesAdapter;
import com.android.meteorid.manager.DataManager;

public class CitiesFragment extends Fragment{

	// ici c'est la liste des villes de l'acceuil ( tous & favoris )

	private static final String ARG_SECTION_NUMBER = "section_number";
	
	private View rootView;

	public static CitiesFragment newInstance(int sectionNumber) {
		CitiesFragment fragment = new CitiesFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public CitiesFragment() {
	}
	
	private BroadcastReceiver receiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			if(getView() == null){
				return;
			}
			String action = intent.getAction();
			if(action.equals(DataManager.LOAD_BASIC_CITY_OK)){
				System.out.println("---gotcha");
				refreshData();
			}
		}
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_main, container,	false);

		// la je recupere dans quel catégorie on est ( tous == 1 , favoris == 2)
		
		if(getArguments().getInt(ARG_SECTION_NUMBER) == 1){
			getActivity().registerReceiver(receiver, new IntentFilter(DataManager.LOAD_BASIC_CITY_OK));
			DataManager myData = DataManager.getInstance(getActivity());
			myData.launchTask();
		}else{
			
		}

		
		return rootView;
	}

	public void refreshData(){
		ListView lv = (ListView) rootView.findViewById(R.id.listview);
		lv.setAdapter(new ListCitiesAdapter(getActivity(), DataManager.getInstance(getActivity()).getBasicCityList()));
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(getActivity(), CityPagerActivity.class);
				intent.putExtra("pos", position);
				startActivity(intent);
			}
		});
	}
	
	@Override
	public void onDestroy() {
		try {
			getActivity().unregisterReceiver(receiver);
		}catch(IllegalArgumentException e){
			e.printStackTrace();
		}
		super.onDestroy();
	}
}
