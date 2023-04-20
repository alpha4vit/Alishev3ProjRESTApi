package by.gurinovich.proj3SprtingProj.models;

import by.gurinovich.proj3SprtingProj.services.SensorService;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Random;

@Entity
@Table(name = "measurement")
public class Measurement implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    @Min(value = -100)
    @Max(value = 100)
    @Column(name = "value")
    private Integer value;

    @NotNull
    @Column(name = "raining")
    private Boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    private Sensor sensor;

    @Column(name = "measured_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date measured_at;


    public Measurement(){}

    public Measurement(boolean makeRandom){
        Random random = new Random();
        this.measured_at = new Date();
        this.raining = random.nextBoolean();
        this.value = (int)(-100+Math.random()*100);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Date getMeasured_at() {
        return measured_at;
    }

    public void setMeasured_at(Date measured_at) {
        this.measured_at = measured_at;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "id=" + id +
                ", value=" + value +
                ", raining=" + raining +
                ", sensor=" + sensor +
                ", measured_at=" + measured_at +
                '}';
    }
}
