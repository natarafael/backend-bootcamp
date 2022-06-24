package com.piracema_monitoramento.demo.controller;

import com.piracema_monitoramento.demo.entities.Antenna;
import com.piracema_monitoramento.demo.entities.dtos.*;
import com.piracema_monitoramento.demo.services.AntennaService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/antennas")
@Slf4j
public class AntennaController {
    private final AntennaService antennaService;
    private final ModelMapper modelMapper;

    public AntennaController( ModelMapper modelMapper, AntennaService antennaService) {
        this.antennaService = antennaService;
        this.modelMapper = modelMapper;
    }


    // Request do tipo Post, recebe no corpo da requisição um JSON "@RequestBody" os dados referentes a uma antena
    // Tenta "try" salvar essa antena no banco atraves do servico "antennaService.save" fornecido pela biblioteca JPA
    // Se nao conseguir salvar ele pega "catch" o erro resultante e retorna o erro para o front/insomnia
    // /antennas
    @PostMapping
    public ResponseEntity<AntennaNoPassesDTO> create(@RequestBody Antenna antenna) {
        try{
            Antenna savedAntenna = antennaService.save(antenna);
            AntennaNoPassesDTO antennaDTO = modelMapper.map(savedAntenna, AntennaNoPassesDTO.class);
            return ResponseEntity.ok(antennaDTO);
        }  catch(Exception e) {
            log.error("Falha durante inserção", e);
            return ResponseEntity.notFound().build();
        }
    }


    // Request do tipo Get, utiliza o serviço fornecido pela JPA para retornar todos as antenas existentes no banco
    // Cria uma lista de antenas que contem somente algumas informações e retorna essa lista
    // /antennas
    @GetMapping
    public ResponseEntity<List<AntennaLocationDTO>> findAll(){
        List<Antenna> antennas;
        antennas = antennaService.findAll();

        List<AntennaLocationDTO> antennaLocationDTOS = antennas
                .stream()
                .map(c ->
                        modelMapper.map(c, AntennaLocationDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(antennaLocationDTOS);
    }


    // Request do tipo Get, recebe na URL da requisição um valor referente ao ID de uma antena
    // Procura a antena pelo id atraves do servico "antennaService.findById" fornecido pela biblioteca JPA
    // Se nao conseguir encontrar ele pega "catch" o erro resultante e retorna o erro para o front/insomnia
    // /antennas/{id}
    @GetMapping("/{id}")
    public ResponseEntity<AntennaDTO> find(@PathVariable Long id) {
        try {
            Antenna antenna = antennaService.findById(id);
            return ResponseEntity.ok(modelMapper.map(antenna,AntennaDTO.class));
        } catch(Exception e) {
            log.error("Antena não encontradra", e);
            return ResponseEntity.notFound().build();
        }
    }


    // Request do tipo Put, recebe no corpo da requisição um JSON "@RequestBody" os dados referentes a uma antena e
    // o ID da antena a ser alterada a partir da URL
    // procurar, a partir do ID, a antena no banco
    // Tenta "try" atualizar essa antena no banco atraves do servico "antennaService.save" fornecido pela biblioteca JPA
    // Se nao conseguir salvar ele pega "catch" o erro resultante e retorna o erro para o front/insomnia
    // /antennas/{id}
    @PutMapping("/{id}")
    public ResponseEntity<AntennaDTO> update(@PathVariable Long id,
                                          @RequestBody Antenna antenna) {
        try {
            Antenna foundAntenna = antennaService.findById(id);
            modelMapper.map(antenna, foundAntenna);
            antennaService.save(foundAntenna);
            return ResponseEntity.ok(modelMapper.map(foundAntenna,AntennaDTO.class));
        } catch(Exception e) {
            log.error("Falha durante atualização", e);
            return ResponseEntity.notFound().build();
        }
    }

    // /antennas/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            antennaService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            log.error("Falha durante remoção", e);
            return ResponseEntity.notFound().build();
        }
    }
}
