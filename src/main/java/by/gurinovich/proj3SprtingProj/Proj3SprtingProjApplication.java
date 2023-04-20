package by.gurinovich.proj3SprtingProj;

import by.gurinovich.proj3SprtingProj.models.Measurement;
import by.gurinovich.proj3SprtingProj.models.Sensor;
import by.gurinovich.proj3SprtingProj.services.MeasurementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@SpringBootApplication
public class Proj3SprtingProjApplication {



	public static void main(String[] args) {
		SpringApplication.run(Proj3SprtingProjApplication.class, args);
//		Random random = new Random();
//		String url = "http://localhost:8080/measurements";
//		RestTemplate restTemplate = new RestTemplate();
////		for (int i =0; i < 1000; ++i){
////			Measurement temp = new Measurement(true);
////			temp.setSensor(new Sensor("amin"));
////			HttpEntity<Measurement> request = new HttpEntity<>(temp);
////			restTemplate.postForObject(url, request, String.class);
////		}
////		restTemplate.getForObject(url, Measurement.class);
////		System.out.println(measurement);

	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
