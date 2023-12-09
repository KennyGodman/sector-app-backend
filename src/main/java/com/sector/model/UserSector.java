package com.sector.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "user_sector")
public class UserSector {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String SectorName;

    private String sectorValue;

    private boolean agreeToTerms;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dateCreated;

    @OneToOne
    private User user;
}