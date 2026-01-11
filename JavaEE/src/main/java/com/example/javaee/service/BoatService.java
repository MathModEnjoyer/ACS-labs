package com.example.javaee.service;

import com.example.javaee.dto.BoatDto;

import java.util.List;

public interface BoatService {
    void save(BoatDto boatDto);
    void update(BoatDto boatDto, Long id);
    void delete(Long id);
    BoatDto getById(Long id);
    List<BoatDto> getAll();
    List<BoatDto> getByYachtsmanId(Long yachtsmanId);
    void saveForYachtsman(BoatDto boat, Long yachtsmanId);
}
