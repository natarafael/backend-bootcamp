package com.piracema_monitoramento.demo.controller;

import com.piracema_monitoramento.demo.entities.Pass;
import com.piracema_monitoramento.demo.entities.dtos.PassDTO;
import com.piracema_monitoramento.demo.entities.dtos.PassFileDTO;
import com.piracema_monitoramento.demo.entities.dtos.PassFormDTO;
import com.piracema_monitoramento.demo.services.PassService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/passes")
@RestController
@Slf4j
public class PassController {

    private final PassService passService;
    private final ModelMapper modelMapper;

    public PassController(PassService passService, ModelMapper modelMapper) {
        this.passService = passService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<PassDTO> create(@RequestBody PassFormDTO pass){
        try {
            Pass savedPass = passService.create(pass);
            PassDTO passDTO = modelMapper.map(savedPass, PassDTO.class);
            return ResponseEntity.ok(passDTO);
        } catch(Exception e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<List<PassFileDTO>> createFromCSV(@RequestParam("csvFile") MultipartFile file) {
        try {
            List<Pass> passes = passService.upload(file);

            List<PassFileDTO> passesDTO = passes.stream()
                    .map(p -> modelMapper.map(p,PassFileDTO.class))
                    .collect(Collectors.toList());
            return ResponseEntity.ok(passesDTO);
        } catch(Exception e) {
            log.error("" + e.getMessage());
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PassDTO>> getAll(){

        List<Pass> passes = passService.findAll();

        List<PassDTO> passesDtos = passes
                .stream()
                .map(p -> modelMapper.map(p, PassDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(passesDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassDTO> getById(@PathVariable Long id){
        try {
            Pass pass = passService.findById(id);
            return ResponseEntity.ok(modelMapper.map(pass,PassDTO.class));
        } catch(Exception e) {
            log.error("Passagem não encontradra", e);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        try {
            passService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch(Exception e) {
            log.error("Falha durante remoção", e);
            return ResponseEntity.notFound().build();
        }
    }
}
