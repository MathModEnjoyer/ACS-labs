package com.example.demo.dto;

import com.example.demo.data.Boat;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoatDto implements Serializable {
    private Long id;
    private String name;
    private String sailNumber;
    private String className;
    private SailorDto owner;

    public Boat toEntity() {
        return new Boat(this.id, this.name, this.sailNumber, this.className, this.owner.toEntity());
    }
}
