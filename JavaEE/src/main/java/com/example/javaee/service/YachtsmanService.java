package com.example.javaee.service;

import com.example.javaee.dto.YachtsmanDto;

import java.util.List;

public interface YachtsmanService {
    void save(YachtsmanDto yachtsmanDto);
    void update(YachtsmanDto yachtsmanDto, Long id);
    void delete(Long id);
    YachtsmanDto getById(Long id);
    List<YachtsmanDto> getAll();
    List<YachtsmanDto> getByBoatId(Long boatId);
}