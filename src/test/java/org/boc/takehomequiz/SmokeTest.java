package org.boc.takehomequiz;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SmokeTest {
  
	@Autowired
	private WeatherController weatherController;
	
    @Test
    public void contextLoadsAndCSVLoads() {
    	assertThat(weatherController).isNotNull();
    	assertThat(weatherController.getWeatherDataSize()).isGreaterThan(0);
    }
}
