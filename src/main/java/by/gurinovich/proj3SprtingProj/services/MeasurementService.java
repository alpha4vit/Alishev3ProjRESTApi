package by.gurinovich.proj3SprtingProj.services;

import by.gurinovich.proj3SprtingProj.models.Measurement;
import by.gurinovich.proj3SprtingProj.models.Sensor;
import by.gurinovich.proj3SprtingProj.repositories.MeasurementRepository;
import by.gurinovich.proj3SprtingProj.utils.MeasurementNotCreatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
@Transactional(readOnly = true)
public class MeasurementService {
    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    public List<Measurement> findAll(){
        return measurementRepository.findAll();
    }

    @Transactional
    public void save(Measurement measurement) throws MeasurementNotCreatedException {
        Sensor sensor = sensorService.findByName(measurement.getSensor().getName());
        if (sensor == null)
            throw new MeasurementNotCreatedException("Sensor with this name doesnt exist");
        measurement.setSensor(sensor);
        measurement.setMeasured_at(new Date());
        measurementRepository.save(measurement);
    }

    public Integer getRainyDaysCount(){
        return measurementRepository.findByRaining(true).size();
    }

}
