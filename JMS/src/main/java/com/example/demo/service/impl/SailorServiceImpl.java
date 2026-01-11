package com.example.demo.service.impl;

import com.example.demo.data.Sailor;
import com.example.demo.dto.SailorDto;
import com.example.demo.repository.SailorRepository;
import com.example.demo.service.SailorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SailorServiceImpl implements SailorService {
    private final SailorRepository sailorRepository;

    @Override
    public void save(SailorDto sailorDto) {
        sailorRepository.save(sailorDto.toEntity());
    }

    @Override
    public void deleteById(Long id) {
        sailorRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        sailorRepository.deleteAll();
    }

    @Override
    public SailorDto findById(Long id) {
        Sailor sailor = sailorRepository.findFirstById(id);
        return sailor == null ? null : sailor.toDto();
    }

    @Override
    public List<SailorDto> findAll() {
        return sailorRepository.findAll().stream().map(Sailor::toDto).collect(Collectors.toList());
    }
}
