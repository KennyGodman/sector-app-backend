package com.sector.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sector.model.DataSectors;
import com.sector.repository.DataRepository;
import com.sector.response.SystemSectorResponse;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class SectorInitializer {

    private final DataRepository dataRepository;

    @PostConstruct
    public void initialize() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String filePath = "src/main/resources/SectorData.json";
            Map<String, Map<String, String>> data = objectMapper.readValue(
                    new File(filePath),
                    new TypeReference<>() {
                    });

            data.forEach((sectorName, sectorData) -> {
                String sectorValue = sectorData.get("value");
                if (!dataRepository.existsBySectorValue(sectorValue)) {
                    DataSectors sector = new DataSectors();
                    sector.setSectorName(sectorName);
                    sector.setSectorValue(sectorValue);
                    dataRepository.save(sector);
                }
            });
        } catch (IOException e) {
            log.error("Error occurred while initializing sector data: {}", e.getMessage());
        }
    }

    public List<SystemSectorResponse> getSystemSector() {
        List<DataSectors> dataSectors = dataRepository.findAll();
        List<SystemSectorResponse> response = new ArrayList<>();

        dataSectors.forEach(dataSector -> {
            SystemSectorResponse systemSectorResponse = new SystemSectorResponse();
            systemSectorResponse.setSectorName(dataSector.getSectorName());
            systemSectorResponse.setSectorValue(dataSector.getSectorValue());
            response.add(systemSectorResponse);
        });
        return response;
    }

    public DataSectors getSectorById(String value){
        return dataRepository.findBySectorValue(value);
    }
}