package com.sector.repository;

import com.sector.model.DataSectors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<DataSectors, String> {

    DataSectors findBySectorValue(String value);

    boolean existsBySectorValue(String sectorValue);
}
