package com.piracema_monitoramento.demo.controller;

import com.piracema_monitoramento.demo.entities.Fish;
import com.piracema_monitoramento.demo.entities.dtos.FishDTO;
import com.piracema_monitoramento.demo.entities.dtos.FishRecaptureDTO;
import com.piracema_monitoramento.demo.entities.dtos.FishSimpleDTO;
import com.piracema_monitoramento.demo.entities.dtos.FishNoPassesDTO;
import com.piracema_monitoramento.demo.services.FishService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/fishes")
@Slf4j
public class FishController {
    private final FishService fishService;
    private final ModelMapper modelMapper;

    private Boolean update = false;

    public FishController( ModelMapper modelMapper, FishService fishService) {
        this.fishService = fishService;
        this.modelMapper = modelMapper;
    }


    // Request do tipo Post, recebe no corpo da requisição um JSON "@RequestBody" os dados referentes a um peixe
    // Tenta "try" salvar esse peixe no banco atraves do servico "fishService.save" fornecido pela biblioteca JPA
    // Se nao conseguir salvar ele pega "catch" o erro resultante e retorna o erro para o front/insomnia
    // /fishes
    @PostMapping
    public ResponseEntity<FishNoPassesDTO> create(@RequestBody Fish fish) {

        try {
            Fish savedFish = fishService.save(fish, update);
            FishNoPassesDTO fishDTO = modelMapper.map(savedFish, FishNoPassesDTO.class);
            return ResponseEntity.ok(fishDTO);
        } catch(Exception e) {
            log.error("Falha durante inserção", e);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/multiple")
    public ResponseEntity<List<FishNoPassesDTO>> create(@RequestBody List<Fish> fishes){
        try{
            List<FishNoPassesDTO> fishNoPassesDTO =  fishes
                    .stream()
                    .map(f -> modelMapper
                            .map(fishService
                                    .save(f, update), FishNoPassesDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(fishNoPassesDTO);

        }catch (Exception e){
            log.error("Falha durante inserção", e);
            return ResponseEntity.notFound().build();
        }
    }

    // Request do tipo Get, utiliza o serviço fornecido pela JPA para retornar todos os peixes existentes no banco
    // Cria uma lista de peixes que contem somente algumas informações e retorna essa lista
    // /fishes
    @GetMapping
    public ResponseEntity<List<FishSimpleDTO>> findAll(){
        List<Fish> fishes;
        fishes = fishService.findAll();

        List<FishSimpleDTO> fishesDTO = fishes
                .stream()
                .map(c ->
                        modelMapper.map(c, FishSimpleDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(fishesDTO);
    }
    // Request do tipo Get, recebe na URL da requisição um valor referente ao ID de um peixe
    // Procura o peixe pelo id atraves do servico "fishService.findById" fornecido pela biblioteca JPA
    // Se nao conseguir encontrar ele pega "catch" o erro resultante e retorna o erro para o front/insomnia
    // /fishes/{id}
    @GetMapping("/{id}")
    public ResponseEntity<FishDTO> find(@PathVariable Long id) {
        try {
            Fish fish = fishService.findById(id);
            return ResponseEntity.ok(modelMapper.map(fish,FishDTO.class));
        } catch(Exception e) {
            log.error("Peixe não encontradro", e);
            return ResponseEntity.notFound().build();
        }
    }

    // /fishes/{pittag}
    @GetMapping("/pittag/{pittag}")
    public ResponseEntity<FishDTO> findByPittag(@PathVariable String pittag) {
        try {
            Fish fish = fishService.findByPittag(pittag);
            return ResponseEntity.ok(modelMapper.map(fish,FishDTO.class));
        } catch(Exception e) {
            log.error("Peixe não encontradro", e);
            return ResponseEntity.notFound().build();
        }
    }

    // /fishes/{pittag}
    @GetMapping("/pittags/{pittag}")
    public ResponseEntity<List<FishDTO>> findAllByPittag(@PathVariable String pittag) {
        try {
            List<Fish> fishList = fishService.findAllByPittag(pittag);

            List<FishDTO> fishDTOList = fishList.stream()
                    .map(c ->
                            modelMapper
                                    .map(c, FishDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fishDTOList);

        } catch(Exception e) {
            log.error("Peixe não encontradro", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/scientificNames/{scientificName}")
    public ResponseEntity<List<FishDTO>> findAllByScientificName(@PathVariable String scientificName) {
        try {
            List<Fish> fishList = fishService.findAllByScientificName(scientificName);

            List<FishDTO> fishDTOList = fishList.stream()
                    .map(c ->
                            modelMapper
                                    .map(c, FishDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fishDTOList);

        } catch(Exception e) {
            log.error("Nome científico da espécie não encontradro", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/captureLocations/{captureLocation}")
    public ResponseEntity<List<FishDTO>> findAllByCaptureLocation(@PathVariable String captureLocation) {
        try {
            List<Fish> fishList = fishService.findAllByCaptureLocation(captureLocation);

            List<FishDTO> fishDTOList = fishList.stream()
                    .map(c ->
                            modelMapper
                                    .map(c, FishDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fishDTOList);

        } catch(Exception e) {
            log.error("O local de captura informado não possui nenhum registro de captura de peixes", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/releaseLocations/{releaseLocation}")
    public ResponseEntity<List<FishDTO>> findAllByReleaseLocation(@PathVariable String releaseLocation) {
        try {
            List<Fish> fishList = fishService.findAllByReleaseLocation(releaseLocation);

            List<FishDTO> fishDTOList = fishList.stream()
                    .map(c ->
                            modelMapper
                                    .map(c, FishDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fishDTOList);

        } catch(Exception e) {
            log.error("O local de soltura informado não possui nenhum registro de soltura de peixes", e);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dnaSamples/{dnaSample}")
    public ResponseEntity<List<FishDTO>> findAllByDnaSample(@PathVariable String dnaSample) {
        try {
            List<Fish> fishList = fishService.findAllByDnaSample(dnaSample);

            List<FishDTO> fishDTOList = fishList.stream()
                    .map(c ->
                            modelMapper
                                    .map(c, FishDTO.class))
                    .collect(Collectors.toList());

            return ResponseEntity.ok(fishDTOList);

        } catch(Exception e) {
            log.error("A amostra de DNA informada não foi encontrada nos registros", e);
            return ResponseEntity.notFound().build();
        }
    }


    // Request do tipo Put, recebe no corpo da requisição um JSON "@RequestBody" os dados referentes a um peixe e
    // o ID do peixe a ser alterado a partir da URL
    // procurar, a partir do ID, o peixe no banco
    // Tenta "try" atualizar esse peixe no banco atraves do servico "fishService.save" fornecido pela biblioteca JPA
    // Se nao conseguir salvar ele pega "catch" o erro resultante e retorna o erro para o front/insomnia
    // /fishes/{id}
    @PutMapping("/{id}")
    public ResponseEntity<FishDTO> update(@PathVariable Long id,
                                         @RequestBody Fish fish) {
        update=true;
        try {
            Fish foundFish = fishService.findById(id);
            modelMapper.map(fish, foundFish);

            List<Fish> fishNames = fishService.findAllByScientificName(foundFish.getScientificName());

            //altera o nome cientifico para todos os peixes que contem o mesmo nome cientifico
            if(!foundFish.getScientificName().isEmpty()){
                fishNames.forEach((f) -> {
                    f.setScientificName(foundFish.getScientificName());
                });
            }

            //altera o nome comum para todos os peixes que contem nome comum
            if(!foundFish.getCommonName().isEmpty()){
                fishNames.forEach((f) -> {
                    f.setCommonName(foundFish.getCommonName());
                });
            }

            List<Fish> fishList = fishService.findAllByPittag(foundFish.getPittag());

            //altera o nome cientifico para todos os peixes que contem a mesma pittag
            if(!foundFish.getScientificName().isEmpty()){
                fishList.forEach((f) -> {
                    f.setScientificName(foundFish.getScientificName());
                });
            }

            //altera o nome comum para todos os peixes que contem a mesma pittag
            if(!foundFish.getCommonName().isEmpty()){
                fishList.forEach((f) -> {
                    f.setCommonName(foundFish.getCommonName());
                });
            }

            //altera o nome tamanho padrao para todos os peixes que contem a mesma pittag
            if(!foundFish.getTotalLength().toString().isEmpty()){
                fishList.forEach((f) -> {
                    f.setTotalLength(foundFish.getTotalLength());
                });
            }

            fishService.save(foundFish, update);

            update=false;
            return ResponseEntity.ok(modelMapper.map(foundFish,FishDTO.class));
        } catch(Exception e) {
            log.error("Falha durante atualização", e);
            return ResponseEntity.notFound().build();
        }
    }

    // /fishes/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            fishService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            log.error("Falha durante remoção", e);
            return ResponseEntity.notFound().build();
        }
    }
}
