package com.piracema_monitoramento.demo.repositories;

import com.piracema_monitoramento.demo.entities.Fish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

//interface pra obter metodos padroes do JPA e criar metodos especificos para ser usado nos serviços "Service"
public interface FishRepository extends JpaRepository<Fish, Long> {

    Optional<Fish> findTopByPittagOrderByIdDesc(String pittag);
    List<Fish> findAllByPittagOrderByIdDesc(String pittag);

    List<Fish> findAllByScientificName(String scientificName);

    List<Fish> findAllByCaptureLocation(String captureLocation);

    List<Fish> findAllByReleaseLocation(String releaseLocation);

    List<Fish> findAllByDnaSample(String dnaSample);

    Optional<Fish> findTopByDnaSampleOrderByIdDesc(String dnaSample);
}
