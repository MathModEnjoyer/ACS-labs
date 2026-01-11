package com.example.spring.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;

import com.example.spring.dto.BoatDto;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
@Builder
public class Boat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String boatClass;

    @ManyToMany
    @JoinTable(
        name = "yachtsman_boat",
        joinColumns = @JoinColumn(name = "boat_id"),
        inverseJoinColumns = @JoinColumn(name = "yachtsman_id")
    )
    private Set<Yachtsman> yachtsmen = new HashSet<>();

    public BoatDto toDto() {
        return BoatDto.builder()
                .id(id)
                .name(name)
                .boatClass(boatClass)
                .build();
    }
}
