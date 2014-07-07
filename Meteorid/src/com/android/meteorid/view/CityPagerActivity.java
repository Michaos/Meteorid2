package com.android.meteorid.view;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.android.meteorid.R;
import com.android.meteorid.adapter.FragmentAdapter;
import com.android.meteorid.dao.City;
import com.android.meteorid.manager.DataManager;

public class CityPagerActivity extends FragmentActivity{

	// cette activity est appeler lors du clic sur une ville dans l'écran d'acceuil

	// Viewpager pour faire defiler les villes et leurs infos ( possede le fragment city fragment )

	private ViewPager viewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(savedInstanceState != null){
			finish();
			return;
		}

		setContentView(R.layout.activity_city);
		
		viewPager = (ViewPager) findViewById(R.id.pager);
		List<Fragment> fragmentList = new ArrayList<Fragment>();
		ArrayList<City> basicCityList = DataManager.getInstance(getApplicationContext()).getBasicCityList();

		for (int i = 0; i < basicCityList.size(); i++) {
			fragmentList.add(new CityFragment().newInstance(basicCityList.get(i)));
		}
		
		FragmentAdapter fa = new FragmentAdapter(getSupportFragmentManager(), fragmentList);
		viewPager.setAdapter(fa);
		viewPager.setCurrentItem(getIntent().getExtras().getInt("pos"));
	}
}