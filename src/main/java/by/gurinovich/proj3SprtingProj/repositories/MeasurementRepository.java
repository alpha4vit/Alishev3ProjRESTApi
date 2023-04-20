package by.gurinovich.proj3SprtingProj.repositories;

import by.gurinovich.proj3SprtingProj.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    List<Measurement> findByRaining(boolean raining);
}
