package com.example.demo.data;

import com.example.demo.dto.BoatDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "boats")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Boat {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "sail_number")
    private String sailNumber;

    @Column(name = "class_name")
    private String className;

    @ManyToOne(targetEntity = Sailor.class, fetch = FetchType.EAGER, optional = false, cascade = {CascadeType.MERGE})
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "owner_id")
    private Sailor owner;

    public BoatDto toDto() {
        return BoatDto.builder()
                .id(this.id)
                .name(this.name)
                .sailNumber(this.sailNumber)
                .className(this.className)
                .owner(this.owner.toDto())
                .build();
    }
}
