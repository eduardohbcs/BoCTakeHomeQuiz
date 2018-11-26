package org.boc.takehomequiz;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Hello world!
 *
 */
@RestController
public class WeatherController {
	private static final Logger LOG = LoggerFactory.getLogger(WeatherController.class);
	
	private static final Map<Integer,WeatherDataPoint> weatherData = new HashMap<>();
	static { // loads CSV file and populates weatherData 	
		File file = null;
		try {
			file = ResourceUtils.getFile("classpath:eng-climate-summary.csv");
		} catch (FileNotFoundException e1) {
			LOG.error("Could not find historical weather data .csv file. Exiting...");
			System.exit(0);
		}
		int count = 0;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for(String line; (line = br.readLine()) != null; ) {
			   if (count == 0) { // skip header line
				   count++;
				   continue;
			   }
			   String[] row = line.split(",");
			   // convert date, and readings
			   Date readingDate = dateFormat.parse(row[2]);
			   Double meanTemp = (row.length >= 4 && !row[3].equals("") ? Double.parseDouble(row[3]) : null);
			   Double highestMonthly = (row.length >= 5 && !row[4].equals("") ? Double.parseDouble(row[4]) : null);
			   Double lowestMonthly = (row.length >= 6 && !row[5].equals("") ? Double.parseDouble(row[5]) : null);
			   WeatherDataPoint dataPoint = new WeatherDataPoint(row[0], row[1], readingDate.getTime(), meanTemp, highestMonthly, lowestMonthly);
			   weatherData.put(dataPoint.getId(), dataPoint);
			}
		} catch (IOException | ParseException e) {
			LOG.error("Error when loading data from .csv file. Details: {}", e);
		}
	}
	
	@RequestMapping(path="/weather", method=GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<WeatherDataPointSummary> getDataPointsByRange(@RequestParam(value="start") long start, @RequestParam(value="end") long end) {
		if (end < start) {
			throw new IllegalArgumentException("End range cannot be smaller than start");
		}
		
		List<WeatherDataPointSummary> result = new ArrayList<>();
		for (WeatherDataPoint weatherDataPoint : weatherData.values()) {
			if (weatherDataPoint.getDate() >= start && weatherDataPoint.getDate() <= end) {
				result.add(weatherDataPoint.getWeatherDataPointSummary());
			}
		}
		Collections.sort(result);
		return result;
	}
	
	@RequestMapping(path="/weather/{id}", method = GET)
	public WeatherDataPoint getDataPointById(@PathVariable @NonNull Integer id) {
	  return weatherData.get(id);
	}
	
	public int getWeatherDataSize() {
		return weatherData.size();
	}
}
