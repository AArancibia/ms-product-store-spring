package com.bodega.api.shared.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RealMappingDto {
    private List<RoleMappingDto> realmMappings;

    @Getter
    @Setter
    public static class RoleMappingDto {
        private String id;
        private String name;
        private Boolean composite;
        private Boolean clientRole;
        private String containerId;
    }
}
