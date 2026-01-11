package com.example.spring.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;

import com.example.spring.dto.YachtsmanDto;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class Yachtsman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;
    
    private String club;

    @ManyToMany(mappedBy = "yachtsmen")
    private Set<Boat> boats = new HashSet<>();

    public YachtsmanDto toDto() {
        return YachtsmanDto.builder()
                .id(id)
                .fullName(fullName)
                .club(club)
                .build();
    }
}
