package com.example.demo.service;

import com.example.demo.dto.BoatDto;
import com.example.demo.dto.SailorDto;

import java.util.List;

public interface BoatService {
    void save(BoatDto boatDto);
    void deleteById(Long id);
    void deleteAll();
    BoatDto findById(Long id);
    List<BoatDto> findAll();
    List<BoatDto> findAllByOwner(SailorDto owner);
}
