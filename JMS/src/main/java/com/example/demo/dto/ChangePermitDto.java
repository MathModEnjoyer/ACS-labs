package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ChangePermitDto {
    private Long id;
    private String berthDto;
    private BoatDto boat;
}
