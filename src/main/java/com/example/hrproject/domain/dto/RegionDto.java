package com.example.hrproject.domain.dto;

import com.example.hrproject.domain.entity.Region;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class RegionDto {

    private Integer id;
    private String regionName;

    public static RegionDto of(Region region) {
        return new RegionDto(
                region.getId(),
                region.getRegionName()
        );
    }
}
