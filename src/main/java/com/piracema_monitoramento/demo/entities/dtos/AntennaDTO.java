package com.piracema_monitoramento.demo.entities.dtos;

import com.piracema_monitoramento.demo.entities.Pass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AntennaDTO {

    private Long id;

    private LocalDateTime registryDate;

    private String latitude;

    private String longitude;

    private LocalDateTime installationDate;

    private LocalDateTime uninstallDate;

    private List<PassDTO> passes;

    private List<StatusAntennaDTO> statusAntenna;
}
