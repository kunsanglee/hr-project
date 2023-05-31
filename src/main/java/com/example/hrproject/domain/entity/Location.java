package com.example.hrproject.domain.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "locations")
@Entity
public class Location {

    @Id
    @Column(name = "location_id")
    private Integer id;

    private String streetAddress;

    private String postalCode;

    private String city;

    private String stateProvince;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    public Location(String streetAddress, String postalCode, String city, String stateProvince, Country country) {
        this.streetAddress = streetAddress;
        this.postalCode = postalCode;
        this.city = city;
        this.stateProvince = stateProvince;
        this.country = country;
    }

    public Location of(String streetAddress, String postalCode, String city, String stateProvince, Country country) {
        return new Location(streetAddress, postalCode, city, stateProvince, country);
    }
}
