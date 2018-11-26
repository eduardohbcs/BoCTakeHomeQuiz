/**
 * 
 */
package org.boc.takehomequiz;

/**
 * A simple wrapper to hold on to a summarized view of a Weather data point (Station_Name, Date, Mean Temp)
 *
 */
public class WeatherDataPointSummary implements Comparable<WeatherDataPointSummary> {
	private Integer id;
	private String stationName;
	private long date;
	private Double meanTemp;
	
	public WeatherDataPointSummary(Integer id, String stationName, long date, Double meanTemp) {
		this.id = id;
		this.stationName = stationName;
		this.date = date;
		this.meanTemp = meanTemp;
	}
	
	@Override
	public int compareTo(WeatherDataPointSummary o) {
		if (date < o.getDate()) {
			return -1;
		} else if (date > o.getDate()) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getStationName() {
		return stationName;
	}
	public long getDate() {
		return date;
	}
	public Double getMeanTemp() {
		return meanTemp;
	} 
}
