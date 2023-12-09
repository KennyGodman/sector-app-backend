package com.sector.repository;

import com.sector.model.UserSector;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectorRepository extends JpaRepository<UserSector, String> {
    Optional<UserSector> findByUser_Id(String id);
}
