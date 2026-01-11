package com.example.javaee.service.impl;

import com.example.javaee.dao.BoatDAO;
import com.example.javaee.dao.YachtsmanDAO;
import com.example.javaee.data.Boat;
import com.example.javaee.data.Yachtsman;
import com.example.javaee.dto.BoatDto;
import com.example.javaee.service.BoatService;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class BoatServiceImpl implements BoatService {

    @Inject
    BoatDAO boatDAO;

    @Override
    public void save(BoatDto boatDto) {
        boatDAO.save(boatDto.toEntity());
    }

    @Override
    public void update(BoatDto boatDto, Long id) {

    Boat boat = boatDAO.getById(id);
    if (boat == null) {
        throw new IllegalArgumentException("Boat with id=" + id + " not found");
    }

    boat.setName(boatDto.getName());
    boat.setBoatClass(boatDto.getBoatClass());

}

    @Override
    public void delete(Long id) {
        boatDAO.deleteById(id);
    }

    @Override
    public BoatDto getById(Long id) {
        return boatDAO.getById(id).toDto();         
    }

    @Override
    public List<BoatDto> getAll() {
        return boatDAO.getAll()
                      .stream()
                      .map(Boat::toDto)            
                      .collect(Collectors.toList());
    }

    @Override
    public List<BoatDto> getByYachtsmanId(Long yachtsmanId) {
        return boatDAO.getByYachtsmanId(yachtsmanId)
                    .stream()
                    .map(Boat::toDto)
                    .collect(Collectors.toList());
    }

    @Inject YachtsmanDAO yachtsmanDAO;

    @Override
    public void saveForYachtsman(BoatDto boatDto, Long yachtsmanId) {

        Boat       boat       = boatDto.toEntity();          
        Yachtsman  yachtsman  = yachtsmanDAO.getById(yachtsmanId);

        boat.getYachtsmen().add(yachtsman);

        yachtsman.getBoats().add(boat);

        boatDAO.save(boat);                               
    }
}
