package com.example.demo.dto;

import com.example.demo.data.Sailor;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class SailorDto implements Serializable {
    private Long id;
    private String fullName;
    private LocalDate birthDate;
    private String country;
    private String club;

    public Sailor toEntity() {
        return new Sailor(this.id, this.fullName, this.birthDate, this.country, this.club);
    }
}
