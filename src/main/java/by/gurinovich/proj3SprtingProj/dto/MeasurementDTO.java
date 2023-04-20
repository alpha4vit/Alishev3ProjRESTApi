package by.gurinovich.proj3SprtingProj.dto;

import by.gurinovich.proj3SprtingProj.models.Sensor;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;

public class MeasurementDTO {

    @NotNull
    @Min(value = -100)
    @Max(value = 100)
    @Column(name = "value")
    private Integer value;

    @NotNull
    @Column(name = "raining")
    private Boolean raining;

    private SensorDTO sensor;

    @Temporal(TemporalType.TIMESTAMP)
    private Date measured_at;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Boolean getRaining() {
        return raining;
    }

    public void setRaining(Boolean raining) {
        this.raining = raining;
    }


    public SensorDTO getSensor() {
        return sensor;
    }

    public void setSensor(SensorDTO sensor) {
        this.sensor = sensor;
    }

    public Date getMeasured_at() {
        return measured_at;
    }

    public void setMeasured_at(Date measured_at) {
        this.measured_at = measured_at;
    }
}
