package com.sector.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class SectorResponse {

    private String id;

    private String sectorName;

    private String sectorValue;

    private String name;
}
