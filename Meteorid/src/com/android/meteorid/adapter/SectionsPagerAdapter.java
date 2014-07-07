package com.android.meteorid.adapter;

import java.util.Locale;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.meteorid.R;
import com.android.meteorid.view.CitiesFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

	private Context mContext;
	
	public SectionsPagerAdapter(Context context, FragmentManager fm) {
		super(fm);
		mContext = context;
	}

	@Override
	public Fragment getItem(int position) {
		return CitiesFragment.newInstance(position + 1);
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		Locale l = Locale.getDefault();
		switch (position) {
		case 0:
			return mContext.getResources().getString(R.string.title_section1).toUpperCase(l);
		case 1:
			return mContext.getResources().getString(R.string.title_section2).toUpperCase(l);
		}
		return null;
	}
}