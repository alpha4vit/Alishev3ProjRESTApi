package by.gurinovich.proj3SprtingProj.controllers;

import by.gurinovich.proj3SprtingProj.models.Sensor;
import by.gurinovich.proj3SprtingProj.services.SensorService;
import by.gurinovich.proj3SprtingProj.utils.ErrorResponse;
import by.gurinovich.proj3SprtingProj.utils.SensorDTOValidator;
import by.gurinovich.proj3SprtingProj.utils.SensorNotCreatedException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import static by.gurinovich.proj3SprtingProj.utils.ErrorResponse.errorThrow;

@RestController
@RequestMapping("/sensor")
public class SensorController {
    private final SensorService sensorService;
    private final ModelMapper modelMapper;
    private final SensorDTOValidator sensorDTOValidator;

    @Autowired
    public SensorController(SensorService sensorService, ModelMapper modelMapper, SensorDTOValidator sensorDTOValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.sensorDTOValidator = sensorDTOValidator;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid by.gurinovich.proj3SprtingProj.dto.SensorDTO sensorDTO, BindingResult bindingResult){
        sensorDTOValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors())
            errorThrow(bindingResult);
        sensorService.save(toSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handle(SensorNotCreatedException ex){
        ErrorResponse errorResponse = new ErrorResponse(
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private Sensor toSensor(by.gurinovich.proj3SprtingProj.dto.SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private by.gurinovich.proj3SprtingProj.dto.SensorDTO toSensorDto(Sensor sensor){
        return modelMapper.map(sensor, by.gurinovich.proj3SprtingProj.dto.SensorDTO.class);
    }

}
