package com.example.demo.repository;

import com.example.demo.data.Boat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoatRepository extends CrudRepository<Boat, Long> {
    Boat findFirstById(Long id);
    @Override
    List<Boat> findAll();
    List<Boat> findAllByOwner_Id(Long id);
}
