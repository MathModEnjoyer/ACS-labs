
package com.example.javaee.dto;

import java.util.HashSet;

import com.example.javaee.data.Boat;
import lombok.*;

/**

DTO-объект для передачи данных о лодке вне JPA-контекста

(REST-слой, UI, сериализация и т. д.).
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoatDto {

private Long id;
private String name;
private String boatClass;


public Boat toEntity() {
return new Boat(this.id, this.name, this.boatClass, new HashSet<>()); 
}

public static BoatDto fromEntity(Boat boat) {
    if (boat == null) return null;
        return BoatDto.builder()
        .id(boat.getId())
        .name(boat.getName())
        .boatClass(boat.getBoatClass())
        .build();
    }
}