/**
 * 
 */
package org.boc.takehomequiz;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A simple wrapper for a historical weather data point containing station_name, province, date, mean temp, 
 * highest monthly max temp and lowest monthly min temp
 *
 *
 */
public class WeatherDataPoint  {
	private static final AtomicInteger IDGENERATOR = new AtomicInteger();
	
	private Integer id;
	private String stationName;
	private String province;
	private long date;
	private Double meanTemp;
	private Double monthlyHighestTemp;
	private Double monthlyLowestTemp;

	public WeatherDataPoint(String stationName, String province, long date, Double meanTemp, Double monthlyHighestTemp, Double monthlyLowestTemp) {
		this.id = IDGENERATOR.getAndIncrement();
		this.stationName = stationName;
		this.province = province;
		this.date = date;
		this.meanTemp = meanTemp;
		this.monthlyHighestTemp = monthlyHighestTemp;
		this.monthlyLowestTemp = monthlyLowestTemp;
	}

	public Integer getId() {
		return id;
	}

	public WeatherDataPointSummary getWeatherDataPointSummary() {
		return new WeatherDataPointSummary(id, stationName, date, meanTemp);
	}
	
	public String getStationName() {
		return stationName;
	}

	public String getProvince() {
		return province;
	}

	public long getDate() {
		return date;
	}

	public Double getMeanTemp() {
		return meanTemp;
	}

	public Double getMonthlyHighestTemp() {
		return monthlyHighestTemp;
	}

	public Double getMonthlyLowestTemp() {
		return monthlyLowestTemp;
	}

}
