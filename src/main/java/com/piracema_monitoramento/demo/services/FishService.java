package com.piracema_monitoramento.demo.services;


import com.piracema_monitoramento.demo.entities.Fish;
import com.piracema_monitoramento.demo.repositories.FishRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@Transactional
public class FishService {

    private final FishRepository fishRepository;

    public FishService(FishRepository fishRepository) {
        this.fishRepository = fishRepository;
    }

    public Fish save(Fish fish, Boolean update){

        Optional<Fish> optionalFish = fishRepository.findTopByPittagOrderByIdDesc(fish.getPittag());

        Optional<Fish> optionalFishDna = fishRepository.findTopByDnaSampleOrderByIdDesc(fish.getDnaSample());

        if(optionalFish.isPresent()) {
            Fish foundedFish = optionalFish.get();

            if(!update){
                if(foundedFish.getPittag().equals(fish.getPittag()) && !fish.getRecapture()){
                    throw new DataIntegrityViolationException("Peixe ja existe e nao foi informado que é uma recaptura");
                }
            }
        }

        if(optionalFishDna.isPresent()) {
            Fish foundedFish = optionalFishDna.get();

            if(!update){
                if(foundedFish.getDnaSample().equals(fish.getDnaSample()) && !fish.getRecapture()){
                    throw new DataIntegrityViolationException("Peixe ja existe e nao foi informado que é uma recaptura");
                }
            }
        }

        return fishRepository.save(fish);
    }

    public List<Fish> findAll(){
        return fishRepository.findAll();
    }

    public Fish findById(Long id){
        Optional<Fish> optionalFish = fishRepository.findById(id);

        if (optionalFish.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optionalFish.get();
    }

    public Fish findByPittag(String pittag) {
        Optional<Fish> optFish = fishRepository.findTopByPittagOrderByIdDesc(pittag);
        if (optFish.isEmpty()) {
            throw new NoSuchElementException();
        }
        return optFish.get();
    }

    public List<Fish> findAllByPittag(String pittag) {

        List<Fish> fishList = fishRepository.findAllByPittagOrderByIdDesc(pittag);

        if (fishList.isEmpty()){
            throw new NoSuchElementException();
        }
        return fishList;
    }

    public List<Fish> findAllByScientificName(String scientificName) {

        List<Fish> fishList = fishRepository.findAllByScientificName(scientificName);

        if (fishList.isEmpty()){
            throw new NoSuchElementException();
        }
        return fishList;
    }

    public void deleteById(Long id) {
        Fish fish = findById(id);
        fishRepository.delete(fish);
    }

    public List<Fish> findAllByCaptureLocation(String captureLocation) {
        List<Fish> fishList = fishRepository.findAllByCaptureLocation(captureLocation);

        if (fishList.isEmpty()){
            throw new NoSuchElementException();
        }
        return fishList;
    }

    public List<Fish> findAllByReleaseLocation(String releaseLocation) {
        List<Fish> fishList = fishRepository.findAllByReleaseLocation(releaseLocation);

        if (fishList.isEmpty()){
            throw new NoSuchElementException();
        }
        return fishList;
    }

    public List<Fish> findAllByDnaSample(String dnaSample) {
        List<Fish> fishList = fishRepository.findAllByDnaSample(dnaSample);

        if (fishList.isEmpty()){
            throw new NoSuchElementException();
        }
        return fishList;
    }
}
