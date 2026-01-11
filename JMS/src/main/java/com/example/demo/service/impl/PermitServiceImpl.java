package com.example.demo.service.impl;

import com.example.demo.data.Permit;
import com.example.demo.dto.BerthDto;
import com.example.demo.dto.BoatDto;
import com.example.demo.dto.PermitDto;
import com.example.demo.repository.PermitRepository;
import com.example.demo.service.PermitService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermitServiceImpl implements PermitService {
    private final PermitRepository permitRepository;
    private final Integer MAX_BERTH_NUM = 3;
    private final Integer MAX_PIER_NUM = 3;
    private final Double DISCOUNTED_FEE = 200.00;
    private final Double STANDARD_FEE = 310.00;

    @Override
    @Transactional
    public void save(PermitDto permitDto) {
        if (permitDto.isBenefits()) {
            permitDto.setFee(DISCOUNTED_FEE);
        } else {
            permitDto.setFee(STANDARD_FEE);
        }
        permitRepository.save(permitDto.toEntity());
    }

    @Override
    public void deleteById(Long id) {
        permitRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        permitRepository.deleteAll();
    }

    @Override
    public PermitDto findById(Long id) {
        Permit permit = permitRepository.findFirstById(id);
        return permit == null ? null : permit.toDto();
    }

    @Override
    public List<PermitDto> findAll() {
        return permitRepository.findAll().stream().map(Permit::toDto).collect(Collectors.toList());
    }

    @Override
    public List<BerthDto> getFreeBerths(BoatDto boatDto) {
        List<BerthDto> freeBerths = new ArrayList<>();
        List<BerthDto> busyBerths = permitRepository
                .findAllByBoat_Id(boatDto.getId())
                .stream().map((e) -> new BerthDto(e.getBerth(), e.getPier()))
                .toList();
        for (int pier = 1; pier <= MAX_PIER_NUM; pier++) {
            for (int berth = 1; berth <= MAX_BERTH_NUM; berth++) {
                BerthDto berthDto = new BerthDto(berth, pier);
                if (!busyBerths.contains(berthDto)) {
                    freeBerths.add(berthDto);
                }
            }
        }
        return freeBerths;
    }
}
