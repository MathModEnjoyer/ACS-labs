package com.example.demo.data;

import com.example.demo.dto.BerthDto;
import com.example.demo.dto.PermitDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "permits")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Permit {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "berth")
    private int berth;

    @Column(name = "pier")
    private int pier;

    @Column(name = "fee")
    private double fee;

    @ManyToOne(targetEntity = Boat.class, fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "boat_id")
    private Boat boat;

    public PermitDto toDto() {
        return PermitDto.builder()
                .id(this.id)
                .fee(this.fee)
                .benefits(this.fee < 250)
                .berthDto(new BerthDto(this.berth, this.pier))
                .boat(this.boat.toDto())
                .build();
    }
}
