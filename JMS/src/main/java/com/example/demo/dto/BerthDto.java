package com.example.demo.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Data
public class BerthDto {
    private int berth;
    private int pier;

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        BerthDto berthDto = (BerthDto) obj;
        return berthDto.getBerth() == this.getBerth() && berthDto.getPier() == this.getPier();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + berth;
        result = prime * result + pier;
        return result;
    }
}
