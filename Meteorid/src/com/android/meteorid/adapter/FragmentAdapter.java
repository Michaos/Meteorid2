package com.android.meteorid.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;

public class FragmentAdapter extends FragmentStatePagerAdapter {
	private List<Fragment> fragments;
	
	public FragmentAdapter(FragmentManager fm, List<Fragment> fragments2) {
		super(fm);
		fragments = fragments2;
	}

	@Override
    public int getCount() {
        return this.fragments.size();
    }

	@Override
	public Fragment getItem(int position) {
		return this.fragments.get(position);
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void destroyItem(View container, int position, Object object) {
	    super.destroyItem(container, position, object);
	}
}