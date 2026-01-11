package com.example.spring.dto;

import java.util.HashSet;
import java.util.List;

import com.example.spring.data.Yachtsman;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class YachtsmanDto {

    private Long id;
    private String fullName;
    private String club;
    private List<BoatDto> boats;

    public Yachtsman toEntity() {
        return new Yachtsman(this.id, this.fullName, this.club, new HashSet<>());
    }

    public static YachtsmanDto fromEntity(Yachtsman yachtsman) {
        if (yachtsman == null) return null;
        return YachtsmanDto.builder()
                .id(yachtsman.getId())
                .fullName(yachtsman.getFullName())
                .club(yachtsman.getClub())
                .build();
    }

    public List<BoatDto> getBoats() {
        return boats;
    }

    public void setBoats(List<BoatDto> boats) {
        this.boats = boats;
    }
}
