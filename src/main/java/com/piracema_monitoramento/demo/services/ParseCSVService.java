package com.piracema_monitoramento.demo.services;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.piracema_monitoramento.demo.entities.dtos.PassFileDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;


@Service
public class ParseCSVService {

    public List<PassFileDTO> parse(MultipartFile file) throws IOException {
        Reader reader = new InputStreamReader(file.getInputStream());
        CSVReader csvReader = new CSVReaderBuilder(reader).build();
    
        CsvToBean<PassFileDTO> csvToBean = new CsvToBeanBuilder(csvReader)
                                                .withSeparator(',')
                                                .withType(PassFileDTO.class)
                                                .build();
    
        List<PassFileDTO> dtos = csvToBean.parse();

        return dtos;
    }
}