package com.example.hrproject.domain.dto;

import com.example.hrproject.domain.entity.Country;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class CountryDto {

    private String id;
    private String countryName;
    private RegionDto region;

    public static CountryDto of(Country country) {
        return new CountryDto(
                country.getId(),
                country.getCountryName(),
                RegionDto.of(country.getRegion())
        );
    }
}
