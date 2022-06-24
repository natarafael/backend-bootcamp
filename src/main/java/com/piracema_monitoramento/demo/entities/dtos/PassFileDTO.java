package com.piracema_monitoramento.demo.entities.dtos;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PassFileDTO {
    @CsvBindByName(column = "antennaId")
    private Long antennaId;
    @CsvBindByName(column = "fishPittag")
    private String fishPittag;
    @CsvBindByName(column = "registryDate")
    @CsvDate(value = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registryDate;
}
