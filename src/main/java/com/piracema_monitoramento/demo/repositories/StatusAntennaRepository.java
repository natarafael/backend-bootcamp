package com.piracema_monitoramento.demo.repositories;

import com.piracema_monitoramento.demo.entities.StatusAntenna;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusAntennaRepository extends JpaRepository<StatusAntenna, Long> {
}
