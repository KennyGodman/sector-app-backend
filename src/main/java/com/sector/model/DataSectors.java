package com.sector.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "dataSector")
public class DataSectors {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String sectorName;

    private String sectorValue;
}