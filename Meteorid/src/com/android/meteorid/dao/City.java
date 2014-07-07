package com.android.meteorid.dao;

import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONObject;

public class City implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private ArrayList<Day> dayList;
	
	public City(JSONObject jsonObject, ArrayList<Day> dayList) {
		this.id = jsonObject.optInt("id");
		this.name = jsonObject.optString("name");
		this.dayList = dayList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Day> getDayList() {
		return dayList;
	}

	public void setDayList(ArrayList<Day> dayList) {
		this.dayList = dayList;
	}
}
