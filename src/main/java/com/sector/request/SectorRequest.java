package com.sector.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SectorRequest {

    @NotBlank(message = "Sector Value is required")
    private String sectorValue;

    private Boolean agreeToTerms;

    @NotBlank(message = "Name is required")
    private String userId;
}
