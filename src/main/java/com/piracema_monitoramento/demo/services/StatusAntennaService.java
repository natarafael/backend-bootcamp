package com.piracema_monitoramento.demo.services;

import com.piracema_monitoramento.demo.entities.Antenna;
import com.piracema_monitoramento.demo.entities.Fish;
import com.piracema_monitoramento.demo.entities.Pass;
import com.piracema_monitoramento.demo.entities.StatusAntenna;
import com.piracema_monitoramento.demo.entities.dtos.PassFileDTO;
import com.piracema_monitoramento.demo.entities.dtos.PassFormDTO;
import com.piracema_monitoramento.demo.entities.dtos.StatusAntennaFormDTO;
import com.piracema_monitoramento.demo.repositories.PassRepository;
import com.piracema_monitoramento.demo.repositories.StatusAntennaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class StatusAntennaService {
    private final StatusAntennaRepository statusAntennaRepository;
    private final AntennaService antennaService;

    public StatusAntennaService(StatusAntennaRepository statusAntennaRepository, AntennaService antennaService) {
        this.statusAntennaRepository = statusAntennaRepository;
        this.antennaService = antennaService;
    }

    public StatusAntenna create(StatusAntennaFormDTO statusAntennaDTO) {
        Antenna foundAntenna = antennaService.findById(statusAntennaDTO.getAntennaID());

        StatusAntenna statusAntenna = new StatusAntenna();

        statusAntenna.setRegistryDate(statusAntennaDTO.getRegistryDate());

        statusAntenna.setStatus(statusAntennaDTO.getStatus());

        statusAntenna.setStatusChangeDate(statusAntennaDTO.getStatusChangeDate());

        statusAntenna.setObservations(statusAntennaDTO.getObservations());

        foundAntenna.getStatusAntennas().add(statusAntenna);

        statusAntenna.setStatusAntenna(foundAntenna);

        return statusAntennaRepository.save(statusAntenna);
    }

    public List<StatusAntenna> findAll() {
        return statusAntennaRepository.findAll();
    }
    public StatusAntenna findById(Long id) {
        Optional<StatusAntenna> optionalStatusAntenna = statusAntennaRepository.findById(id);
        if (optionalStatusAntenna.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optionalStatusAntenna.get();
    }
}
