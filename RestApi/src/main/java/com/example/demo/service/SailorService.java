package com.example.demo.service;

import com.example.demo.dto.SailorDto;

import java.util.List;

public interface SailorService {
    void save(SailorDto sailorDto);
    void deleteById(Long id);
    void deleteAll();
    SailorDto findById(Long id);
    List<SailorDto> findAll();
}
