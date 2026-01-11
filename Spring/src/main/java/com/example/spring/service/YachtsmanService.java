package com.example.spring.service;

import java.util.List;

import com.example.spring.dto.YachtsmanDto;

public interface YachtsmanService {
    void save(YachtsmanDto yachtsmanDto);
    void update(YachtsmanDto yachtsmanDto, Long id);
    void delete(Long id);
    YachtsmanDto getById(Long id);
    List<YachtsmanDto> getAll();
    List<YachtsmanDto> getByBoatId(Long boatId);
}