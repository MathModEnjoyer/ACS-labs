package com.example.demo.service;

import com.example.demo.dto.BerthDto;
import com.example.demo.dto.BoatDto;
import com.example.demo.dto.PermitDto;

import java.util.List;

public interface PermitService {
    void save(PermitDto permitDto);
    void deleteById(Long id);
    void deleteAll();
    PermitDto findById(Long id);
    List<PermitDto> findAll();
    List<BerthDto> getFreeBerths(BoatDto boatDto);
}
