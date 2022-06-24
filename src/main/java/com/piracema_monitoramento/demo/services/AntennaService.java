package com.piracema_monitoramento.demo.services;

import com.piracema_monitoramento.demo.entities.Antenna;
import com.piracema_monitoramento.demo.repositories.AntennaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class AntennaService {

    private final AntennaRepository antennaRepository;

    public AntennaService(AntennaRepository antennaRepository) {
        this.antennaRepository = antennaRepository;
    }

    public Antenna save(Antenna antenna){
        return  antennaRepository.save(antenna);
    }

    public List<Antenna> findAll(){
        return antennaRepository.findAll();
    }

    public Antenna findById(Long id){
        Optional<Antenna> optionalAntenna = antennaRepository.findById(id);

        if (optionalAntenna.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optionalAntenna.get();
    }

    public void deleteById(Long id) {
        Antenna antenna = findById(id);
        antennaRepository.delete(antenna);
    }
}
