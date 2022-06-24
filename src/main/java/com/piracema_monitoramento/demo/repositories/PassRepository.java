package com.piracema_monitoramento.demo.repositories;

import com.piracema_monitoramento.demo.entities.Pass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassRepository extends JpaRepository<Pass, Long> {
}
