package by.gurinovich.proj3SprtingProj.utils;
import by.gurinovich.proj3SprtingProj.dto.SensorDTO;
import by.gurinovich.proj3SprtingProj.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorDTOValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorDTOValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> a) {
        return SensorDTO.class.equals(a);
    }

    @Override
    public void validate(Object object, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) object;
        sensorService.findByName(sensorDTO.getName());
        if (sensorService.findByName(sensorDTO.getName()) != null){
            errors.rejectValue("name", "", "This name is already used");
        }
    }
}
