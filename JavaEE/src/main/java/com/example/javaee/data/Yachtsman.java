package com.example.javaee.data;

import java.util.HashSet;
import java.util.Set;

import com.example.javaee.dto.YachtsmanDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class Yachtsman {

    public YachtsmanDto toDto() {
        return YachtsmanDto.builder()
                        .id(id)
                        .fullName(fullName)
                        .club(club)
                        .build();
        }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    private String club;

    @ManyToMany(mappedBy = "yachtsmen")
    private Set<Boat> boats = new HashSet<>();

}