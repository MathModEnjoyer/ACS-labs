package com.example.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.data.Boat;

import java.util.List;

@Repository
public interface BoatRepository extends JpaRepository<Boat, Long> {

    Boat findFirstById(Long id);

    // кастомный метод для выборки по яхтсмену
    List<Boat> findAllByYachtsmen_Id(Long yachtsmanId);
}
