package com.example.hrproject.domain.dto;

import com.example.hrproject.domain.entity.Location;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LocationDto {

    private Integer id;
    private String streetAddress;
    private String postalCode;
    private String city;
    private String stateProvince;
    private CountryDto country;

    public static LocationDto of(Location location) {
        return new LocationDto(
                location.getId(),
                location.getStreetAddress(),
                location.getPostalCode(),
                location.getCity(),
                location.getStateProvince(),
                CountryDto.of(location.getCountry())
        );
    }
}
