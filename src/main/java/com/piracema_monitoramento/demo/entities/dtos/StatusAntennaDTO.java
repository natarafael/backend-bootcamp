package com.piracema_monitoramento.demo.entities.dtos;

import com.piracema_monitoramento.demo.entities.Antenna;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusAntennaDTO {

    private Long id;

    private LocalDateTime registryDate;

    private Boolean status;

    private LocalDateTime statusChangeDate;

    private String observations;

    private AntennaNoStatusAntennaDTO antenna;
}
