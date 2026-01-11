package com.example.spring.service;

import java.util.List;

import com.example.spring.dto.BoatDto;

public interface BoatService {
    void save(BoatDto boatDto);
    void update(BoatDto boatDto, Long id);
    void delete(Long id);
    BoatDto getById(Long id);
    List<BoatDto> getAll();
    List<BoatDto> getByYachtsmanId(Long yachtsmanId);
    void saveForYachtsman(BoatDto boat, Long yachtsmanId);
}
