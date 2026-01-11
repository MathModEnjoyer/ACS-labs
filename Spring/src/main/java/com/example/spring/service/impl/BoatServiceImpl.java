package com.example.spring.service.impl;

import com.example.spring.data.Boat;
import com.example.spring.data.Yachtsman;
import com.example.spring.dto.BoatDto;
import com.example.spring.repository.BoatRepository;
import com.example.spring.repository.YachtsmanRepository;
import com.example.spring.service.BoatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoatServiceImpl implements BoatService {

    private final BoatRepository boatRepo;
    private final YachtsmanRepository yRepo;

    @Override
    public void save(BoatDto dto) {
        boatRepo.save(dto.toEntity());
    }

    @Override
    public void update(BoatDto dto, Long id) {
        Boat boat = boatRepo.findById(id)
                .orElseThrow(() ->
                        new IllegalArgumentException("Boat id=" + id + " not found"));
        boat.setName(dto.getName());
        boat.setBoatClass(dto.getBoatClass());
    }

    @Override
    public void delete(Long id) {
        boatRepo.deleteById(id);
    }

    @Override
    public BoatDto getById(Long id) {
        return BoatDto.fromEntity(
                boatRepo.findById(id)
                        .orElseThrow(() ->
                                new IllegalArgumentException("Boat id=" + id + " not found")));
    }

    @Override
    public List<BoatDto> getAll() {
        return boatRepo.findAll()
                .stream()
                .map(BoatDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<BoatDto> getByYachtsmanId(Long yId) {
        return boatRepo.findAllByYachtsmen_Id(yId)
                .stream()
                .map(BoatDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void saveForYachtsman(BoatDto dto, Long yId) {
        Yachtsman yachtsman = yRepo.findById(yId)
                .orElseThrow(() ->
                        new IllegalArgumentException("Yachtsman id=" + yId + " not found"));

        Boat boat = dto.toEntity();
        boat.getYachtsmen().add(yachtsman);
        yachtsman.getBoats().add(boat);

        boatRepo.save(boat);
        yRepo.save(yachtsman);  // ✅ ИСПРАВЛЕНИЕ: Сохраняем яхтсмена тоже!
    }
}
