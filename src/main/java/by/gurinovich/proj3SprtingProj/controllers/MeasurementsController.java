package by.gurinovich.proj3SprtingProj.controllers;

import by.gurinovich.proj3SprtingProj.dto.MeasurementDTO;
import by.gurinovich.proj3SprtingProj.models.Measurement;
import by.gurinovich.proj3SprtingProj.models.MeasurementResponse;
import by.gurinovich.proj3SprtingProj.services.MeasurementService;
import by.gurinovich.proj3SprtingProj.utils.ErrorResponse;
import by.gurinovich.proj3SprtingProj.utils.MeasurementNotCreatedException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import static by.gurinovich.proj3SprtingProj.utils.ErrorResponse.errorThrow;
import java.util.List;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;

    @Autowired
    public MeasurementsController(MeasurementService measurementService, ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
    }

    @GetMapping()
    public MeasurementResponse getMeasurements() {
        MeasurementResponse measurementResponse = new MeasurementResponse(measurementService.findAll().stream().map(this::toMeasurementDTO).toList());
        ;
        return measurementResponse;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            errorThrow(bindingResult);
        measurementService.save(toMeasurement(measurementDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handle(MeasurementNotCreatedException ex){
        ErrorResponse response = new ErrorResponse(
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    private Measurement toMeasurement(MeasurementDTO measurementDTO){
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO toMeasurementDTO(Measurement measurement){
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

    @GetMapping("/rainyDaysCount")
    public Integer getRainyDaysCount(){
        return measurementService.getRainyDaysCount();
    }
}
