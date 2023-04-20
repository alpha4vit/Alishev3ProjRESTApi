package by.gurinovich.proj3Client;


import by.gurinovich.proj3Client.dto.MeasurementDTO;
import by.gurinovich.proj3Client.dto.MeasurementResponse;
import by.gurinovich.proj3Client.dto.SensorDTO;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootApplication
public class Proj3ClientApplication {

	public static void main(String[] args) {
		final RestTemplate restTemplate = new RestTemplate();

		List<Integer> values = getValues(restTemplate);
		drawChart(values);
	}

	private static List<Integer> getValues(RestTemplate restTemplate){
		final String url = "http://localhost:8080/measurements";
		MeasurementResponse jsonResponse = restTemplate.getForObject(url, MeasurementResponse.class);
		return jsonResponse.getMeasurements().stream()
				.map(MeasurementDTO::getValue)
				.limit(100)
				.toList();

	}

	private static void genMeasurements(RestTemplate restTemplate){
		String url = "http://localhost:8080/measurements/add";
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		for (int i =0; i < 500; ++i){
			HttpEntity<Object> request = new HttpEntity<>(genRandMeas(), headers);
			restTemplate.postForObject(url, request, String.class);
		}
	}

	private static MeasurementDTO genRandMeas(){
		Random random = new Random();
		MeasurementDTO measurementDTO = new MeasurementDTO();
		measurementDTO.setRaining(random.nextBoolean());
		measurementDTO.setValue((int)(-100+Math.random()*100));
		measurementDTO.setSensor(new SensorDTO("amin"));
		return measurementDTO;

	}

	public static void drawChart(List<Integer> values){
		XYChart xyChart = new XYChart(4000, 500);
		ArrayList<Integer> xPositions = new ArrayList<>();
		for (int i =1; i <= values.size(); ++i)
			xPositions.add(i);
		xyChart.addSeries("Weather temperatures", xPositions, values);
		new SwingWrapper<XYChart>(xyChart).displayChart();
	}


}
