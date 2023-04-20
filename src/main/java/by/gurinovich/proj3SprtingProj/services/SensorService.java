package by.gurinovich.proj3SprtingProj.services;

import by.gurinovich.proj3SprtingProj.models.Sensor;
import by.gurinovich.proj3SprtingProj.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Sensor getSensorById(Integer id){
        return sensorRepository.findById(id).orElse(null);
    }

    public Sensor findByName(String name){
        return sensorRepository.findByName(name);
    }

    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }
}
