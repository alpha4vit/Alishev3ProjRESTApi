package by.gurinovich.proj3SprtingProj.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

public class ErrorResponse {
    private String name;
    private long timestamp;

    public ErrorResponse(String name, long timestamp) {
        this.name = name;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public static void errorThrow(BindingResult bindingResult) throws MeasurementNotCreatedException{
        StringBuilder error = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError er:errors){
            error.append(er.getField())
                    .append("-")
                    .append(er.getDefaultMessage());
        }
        throw new MeasurementNotCreatedException(error.toString());
    }

}
