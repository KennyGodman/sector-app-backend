package com.sector.service;


import com.sector.model.DataSectors;
import com.sector.model.User;
import com.sector.model.UserSector;
import com.sector.repository.SectorRepository;
import com.sector.request.SectorRequest;
import com.sector.response.SectorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SectorService {

    private final SectorRepository sectorRepository;

    private final UserService userService;

    private final SectorInitializer sectorInitializer;


    public SectorResponse createSector(SectorRequest sectorRequest) {
        validateSectorRequest(sectorRequest);

        User foundUser = userService.getUserById(sectorRequest.getUserId());

        UserSector foundUserSector = getSectorByUserId(foundUser.getId());

        DataSectors foundDataSector = sectorInitializer.getSectorById(sectorRequest.getSectorValue());
        if (foundDataSector == null) {
            throw new RuntimeException("Sector not found, Please enter a Valid Sector Value");
        }


        if (foundUserSector == null) {
            foundUserSector = new UserSector();
        }

        foundUserSector.setSectorName(foundDataSector.getSectorName());
        foundUserSector.setSectorValue(foundDataSector.getSectorValue());
        foundUserSector.setAgreeToTerms(sectorRequest.getAgreeToTerms());
        foundUserSector.setUser(foundUser);
        UserSector savedUserSector = saveSector(foundUserSector);

        return SectorResponse.builder()
                .id(savedUserSector.getId())
                .sectorName(savedUserSector.getSectorName())
                .sectorValue(savedUserSector.getSectorValue())
                .name(foundUser.getName())
                .build();
    }

    private void validateSectorRequest(SectorRequest sectorRequest) {
        if (sectorRequest == null) {
            throw new RuntimeException("UserSector request cannot be null");
        }

        if (sectorRequest.getUserId() == null) {
            throw new RuntimeException("User id cannot be null");
        }

        if (sectorRequest.getSectorValue() == null) {
            throw new RuntimeException("UserSector Value cannot be null");
        }

        if (!sectorRequest.getAgreeToTerms()) {
            throw new RuntimeException("You must agree to the terms and conditions");
        }
    }


    public SectorResponse getSectorByUser(String id) {
        User foundUser = userService.getUserById(id);
        UserSector foundUserSector = getSectorByUserId(foundUser.getId());

        return SectorResponse.builder()
                .id(foundUserSector.getId())
                .sectorName(foundUserSector.getSectorName())
                .sectorValue(foundUserSector.getSectorValue())
                .name(foundUser.getName())
                .build();
    }


    public UserSector getSectorByUserId(String id) {
        return sectorRepository.findByUser_Id(id).orElse(null);
    }

    public UserSector saveSector(UserSector userSector) {
        return sectorRepository.save(userSector);
    }
}
