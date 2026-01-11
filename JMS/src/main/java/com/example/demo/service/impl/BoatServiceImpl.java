package com.example.demo.service.impl;

import com.example.demo.data.Boat;
import com.example.demo.dto.BoatDto;
import com.example.demo.dto.SailorDto;
import com.example.demo.repository.BoatRepository;
import com.example.demo.service.BoatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoatServiceImpl implements BoatService {
    private final BoatRepository boatRepository;

    @Override
    public void save(BoatDto boatDto) {
        boatRepository.save(boatDto.toEntity());
    }

    @Override
    public void deleteById(Long id) {
        boatRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        boatRepository.deleteAll();
    }

    @Override
    public BoatDto findById(Long id) {
        Boat boat = boatRepository.findFirstById(id);
        if (boat == null) {
            return null;
        }
        return boat.toDto();
    }

    @Override
    public List<BoatDto> findAll() {
        return boatRepository.findAll().stream().map(Boat::toDto).collect(Collectors.toList());
    }

    @Override
    public List<BoatDto> findAllByOwner(SailorDto owner) {
        return boatRepository.findAllByOwner_Id(owner.getId())
                .stream().map(Boat::toDto)
                .collect(Collectors.toList());
    }
}
