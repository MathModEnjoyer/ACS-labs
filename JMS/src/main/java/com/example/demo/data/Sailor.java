package com.example.demo.data;

import com.example.demo.dto.SailorDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sailors")
public class Sailor {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "country")
    private String country;

    @Column(name = "club")
    private String club;

    public SailorDto toDto() {
        return SailorDto.builder()
                .id(this.id)
                .fullName(this.fullName)
                .birthDate(this.birthDate)
                .country(this.country)
                .club(this.club)
                .build();
    }

    @Override
    public String toString() {
        return "fullName: " + fullName + "; birthDate: " + birthDate + "; country: " + country + "; club: " + club;
    }
}
