package by.gurinovich.proj3Client.dto;

import java.util.List;

public class MeasurementResponse {
    private List<MeasurementDTO> measurements;

    public List<MeasurementDTO> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<MeasurementDTO> measurements) {
        this.measurements = measurements;
    }
}
