package by.gurinovich.proj3SprtingProj.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.Serializable;

@Component
public class SensorDTO {
    @Column(name = "name")
    @NotEmpty
    @Size(min = 2, max = 30, message = "Size should be between 2 and 30 characters")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
