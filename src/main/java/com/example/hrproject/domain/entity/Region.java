package com.example.hrproject.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "regions")
@Entity
public class Region {

    @Id
    @Column(name = "region_id")
    private Integer id;

    private String regionName;

    private Region(String regionName) {
        this.regionName = regionName;
    }

    public Region of(String regionName) {
        return new Region(regionName);
    }

}
