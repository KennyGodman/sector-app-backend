package com.sector.controller;

import com.sector.request.SectorRequest;
import com.sector.response.SectorResponse;
import com.sector.response.SystemSectorResponse;
import com.sector.service.SectorInitializer;
import com.sector.service.SectorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sector")
@RestController
@RequiredArgsConstructor
public class SectorController {

    private final SectorService sectorService;

    private final SectorInitializer sectorInitializer;

    @PostMapping("/create")
    public SectorResponse createSector(@Valid @RequestBody SectorRequest request){
        return sectorService.createSector(request);
    }

    @GetMapping("/getSector")
    public SectorResponse getSectorByUserId(@RequestParam("userId") String userId){
        return sectorService.getSectorByUser(userId);
    }

    @GetMapping("/all")
    public List<SystemSectorResponse> getSectorByUserId(){
        return sectorInitializer.getSystemSector();
    }
}
