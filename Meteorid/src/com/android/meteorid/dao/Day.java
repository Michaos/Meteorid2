package com.android.meteorid.dao;

import java.io.Serializable;

import org.json.JSONObject;

public class Day implements Serializable{
	
	private static final long serialVersionUID = 4951145426140695182L;
	private Integer timestamp;
	private Integer tempDay;
	private Integer tempMin;
	private Integer tempMax;
	private Integer tempMorning;
	private Integer tempEvening;
	private Integer tempNight;
	private String description;
	private String icon;
	
	public Day(JSONObject jsonObject) {
		this.timestamp = jsonObject.optInt("timestamp");
		this.tempDay = jsonObject.optJSONObject("temp").optInt("day");
		this.tempMin = jsonObject.optJSONObject("temp").optInt("min");		
		this.tempMax = jsonObject.optJSONObject("temp").optInt("max");
		this.tempMorning = jsonObject.optJSONObject("temp").optInt("morn");
		this.tempEvening = jsonObject.optJSONObject("temp").optInt("eve");
		this.tempNight = jsonObject.optJSONObject("temp").optInt("night");
		this.description = jsonObject.optJSONArray("weather").optJSONObject(0).optString("description");
		this.icon = jsonObject.optJSONArray("weather").optJSONObject(0).optString("icon");;
	}

	public Integer getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Integer timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getTempDay() {
		return tempDay;
	}

	public void setTempDay(Integer tempDay) {
		this.tempDay = tempDay;
	}

	public Integer getTempMin() {
		return tempMin;
	}

	public void setTempMin(Integer tempMin) {
		this.tempMin = tempMin;
	}

	public Integer getTempMax() {
		return tempMax;
	}

	public void setTempMax(Integer tempMax) {
		this.tempMax = tempMax;
	}

	public Integer getTempMorning() {
		return tempMorning;
	}

	public void setTempMorning(Integer tempMorning) {
		this.tempMorning = tempMorning;
	}

	public Integer getTempEvening() {
		return tempEvening;
	}

	public void setTempEvening(Integer tempEvening) {
		this.tempEvening = tempEvening;
	}

	public Integer getTempNight() {
		return tempNight;
	}

	public void setTempNight(Integer tempNight) {
		this.tempNight = tempNight;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}
}
