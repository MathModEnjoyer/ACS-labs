package com.example.javaee.data;

import java.util.HashSet;
import java.util.Set;

import com.example.javaee.dto.BoatDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
public class Boat {

    public BoatDto toDto() {
        return BoatDto.builder()
                    .id(id)
                    .name(name)
                    .boatClass(boatClass)
                    .build();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   
    private String name;

    
    private String boatClass;


    @ManyToMany
    @JoinTable(name = "yachtsman_boat",
            joinColumns        = @JoinColumn(name = "boat_id"),
            inverseJoinColumns = @JoinColumn(name = "yachtsman_id"))
    private Set<Yachtsman> yachtsmen = new HashSet<>();


}