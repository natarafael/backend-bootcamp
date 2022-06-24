package com.piracema_monitoramento.demo.repositories;

import com.piracema_monitoramento.demo.entities.Antenna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AntennaRepository extends JpaRepository<Antenna, Long> {
}
