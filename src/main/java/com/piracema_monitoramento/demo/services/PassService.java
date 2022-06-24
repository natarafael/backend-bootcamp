package com.piracema_monitoramento.demo.services;

import com.piracema_monitoramento.demo.entities.Antenna;
import com.piracema_monitoramento.demo.entities.Fish;
import com.piracema_monitoramento.demo.entities.Pass;
import com.piracema_monitoramento.demo.entities.dtos.PassFileDTO;
import com.piracema_monitoramento.demo.entities.dtos.PassFormDTO;
import com.piracema_monitoramento.demo.repositories.PassRepository;
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
public class PassService {
    private final PassRepository passRepository;

    private final FishService fishService;

    private final AntennaService antennaService;
    private final ParseCSVService parseCSVService;

    public PassService(PassRepository passRepository, FishService fishService, AntennaService antennaService, ParseCSVService parseCSVService) {
        this.passRepository = passRepository;
        this.fishService = fishService;
        this.antennaService = antennaService;
        this.parseCSVService = parseCSVService;
    }


    public Pass create(PassFormDTO passDTO) {
        Fish foundFish = fishService.findById(passDTO.getFishId());
        Antenna foundAntenna = antennaService.findById(passDTO.getAntennaId());

        Pass pass = new Pass();

        foundFish.getPasses().add(pass);
        pass.setFish(foundFish);

        foundAntenna.getPasses().add(pass);
        pass.setAntennaPass(foundAntenna);

        return passRepository.save(pass);
    }

    public Pass create(PassFileDTO passDTO) {

        Fish foundFish = fishService.findByPittag(passDTO.getFishPittag());
        Antenna foundAntenna = antennaService.findById(passDTO.getAntennaId());

        Pass pass = new Pass();

        pass.setRegistryDate(passDTO.getRegistryDate());

        foundFish.getPasses().add(pass);
        pass.setFish(foundFish);

        foundAntenna.getPasses().add(pass);
        pass.setAntennaPass(foundAntenna);

        return passRepository.save(pass);
    }

    public List<Pass> upload(MultipartFile file) throws IOException {
        List<PassFileDTO> passesDTO = parseCSVService.parse(file);

        return passesDTO.stream().map(c -> create(c)).collect(Collectors.toList());
    }

    public Pass save(Pass pass){
        return passRepository.save(pass);
    }

    public List<Pass> findAll() {
        return passRepository.findAll();
    }
    public Pass findById(Long id) {
        Optional<Pass> optPass = passRepository.findById(id);
        if (optPass.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optPass.get();
    }

    public void deleteById(Long id) {
        Pass pass = findById(id);
        passRepository.delete(pass);
    }
}
