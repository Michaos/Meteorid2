package com.android.meteorid.manager;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;

import com.android.meteorid.dao.City;
import com.android.meteorid.dao.Day;
import com.android.meteorid.utils.Utils;

public class DataManager {

	private static final String TAG = "DataManager";
	public static final String LOAD_BASIC_CITY_OK = "loadBasicCityOk";
	public static String CITY_CACHE_NAME = "CACHE_METEORID_CITY_"; 

	private static DataManager instance;
	private static Context mContext;

	private ArrayList<City> basicCityList;

	private DataManager() {
	}

	public static DataManager getInstance(Context context) {
		if (context != null) {
			mContext = context;
		}
		if (instance == null) {
			instance = new DataManager();
		}
		return instance;
	}

	public void launchTask() {

		basicCityList = new ArrayList<City>();

		for (int i = 0; i < MappingManager.CITIES.length; i++) {
			JSONObject cache = CacheManager.loadCache(MappingManager.DIR_DATA, CITY_CACHE_NAME+Integer.toString(MappingManager.CITIES[i]));
			if(cache != null){
				System.out.println("---cach != null");
				try {
					JSONArray jsonArray = cache.getJSONArray("list");
					ArrayList<Day> dayList = new ArrayList<Day>();
					for (int j = 0; j < jsonArray.length(); j++) {
						dayList.add(new Day(jsonArray.getJSONObject(j)));
					}
					JSONObject jsonObject = cache.getJSONObject("city");
					basicCityList.add(new City(jsonObject, dayList));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		mContext.sendBroadcast(new Intent(LOAD_BASIC_CITY_OK));
		Utils.execute(new getJson());
	}

	private class getJson extends AsyncTask<Integer, Object, Boolean> {

		@Override
		protected Boolean doInBackground(Integer... params) {

			ArrayList<City> basicCityListTemp = new ArrayList<City>();

			for (int i = 0; i < MappingManager.CITIES.length; i++) {
				String urlApi = FluxManager.URL_API.replaceAll("__ID__", Integer.toString(MappingManager.CITIES[i]));

				System.out.println(urlApi);

				JSONObject jsonFlux = ApiManager.callAPI(urlApi);
				CacheManager.createCache(jsonFlux, MappingManager.DIR_DATA, CITY_CACHE_NAME+Integer.toString(MappingManager.CITIES[i]));

				if(jsonFlux != null){
					try {
						JSONArray jsonArray = jsonFlux.getJSONArray("list");
						ArrayList<Day> dayList = new ArrayList<Day>();
						for (int j = 0; j < jsonArray.length(); j++) {
							dayList.add(new Day(jsonArray.getJSONObject(j)));
						}
						JSONObject jsonObject = jsonFlux.getJSONObject("city");
						basicCityListTemp.add(new City(jsonObject, dayList));
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
			basicCityList = basicCityListTemp;
			return true;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			if(result){
				mContext.sendBroadcast(new Intent(LOAD_BASIC_CITY_OK));
			}
			super.onPostExecute(result);
		}
	}

	public ArrayList<City> getBasicCityList(){
		return basicCityList;
	}
}
