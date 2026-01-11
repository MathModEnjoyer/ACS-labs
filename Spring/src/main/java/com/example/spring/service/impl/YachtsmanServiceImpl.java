package com.example.spring.service.impl;

import com.example.spring.data.Yachtsman;
import com.example.spring.dto.YachtsmanDto;
import com.example.spring.repository.YachtsmanRepository;
import com.example.spring.service.YachtsmanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class YachtsmanServiceImpl implements YachtsmanService {

    private final YachtsmanRepository repo;

    @Override
    public void save(YachtsmanDto dto) {
        repo.save(dto.toEntity());
    }

    @Override
    public void update(YachtsmanDto dto, Long id) {
        Yachtsman y = repo.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Yachtsman id=" + id + " not found"));
        y.setFullName(dto.getFullName());
        y.setClub(dto.getClub());
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public YachtsmanDto getById(Long id) {
        return YachtsmanDto.fromEntity(
                repo.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException("Yachtsman id=" + id + " not found")));
    }

    @Override
    public List<YachtsmanDto> getAll() {
        return repo.findAll()
                .stream()
                .map(YachtsmanDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<YachtsmanDto> getByBoatId(Long boatId) {
        return repo.findAllByBoats_Id(boatId)
                .stream()
                .map(YachtsmanDto::fromEntity)
                .collect(Collectors.toList());
    }
}
