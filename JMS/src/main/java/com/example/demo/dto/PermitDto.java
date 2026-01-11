package com.example.demo.dto;

import com.example.demo.data.Permit;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PermitDto implements Serializable {
    private Long id;
    private BerthDto berthDto;
    private double fee;
    private boolean benefits;
    private BoatDto boat;

    public Permit toEntity() {
        return new Permit(this.id, this.berthDto.getBerth(), this.berthDto.getPier(), this.fee, this.boat.toEntity());
    }
}
