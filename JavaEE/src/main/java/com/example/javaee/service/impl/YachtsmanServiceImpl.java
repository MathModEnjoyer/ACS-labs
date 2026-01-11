package com.example.javaee.service.impl;

import com.example.javaee.dao.YachtsmanDAO;
import com.example.javaee.data.Yachtsman;
import com.example.javaee.dto.YachtsmanDto;
import com.example.javaee.service.YachtsmanService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class YachtsmanServiceImpl implements YachtsmanService {

    @Inject
    YachtsmanDAO yachtsmanDAO;

    @Override
    public void save(YachtsmanDto yachtsmanDto) {
        yachtsmanDAO.save(yachtsmanDto.toEntity());
    }

    @Override
    public void update(YachtsmanDto yachtsmanDto, Long id) {
        Yachtsman yachtsman = yachtsmanDto.toEntity();
        yachtsman.setId(id);
        yachtsmanDAO.update(yachtsman);
    }

    @Override
    public void delete(Long id) {
        yachtsmanDAO.deleteById(id);
    }

    @Override
    public YachtsmanDto getById(Long id) {
        return yachtsmanDAO.getById(id).toDto(); 
    }

    @Override
    public List<YachtsmanDto> getAll() {
        return yachtsmanDAO.getAll()
                           .stream()
                           .map(Yachtsman::toDto) 
                           .collect(Collectors.toList());
    }

    @Override
    public List<YachtsmanDto> getByBoatId(Long boatId) {
        return yachtsmanDAO.getByBoatId(boatId)
                        .stream()
                        .map(Yachtsman::toDto)
                        .collect(Collectors.toList());
    }
    
}
