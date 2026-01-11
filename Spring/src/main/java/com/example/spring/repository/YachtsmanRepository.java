package com.example.spring.repository;

import com.example.spring.data.Yachtsman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface YachtsmanRepository extends JpaRepository<Yachtsman, Long> {

    Yachtsman findFirstById(Long id);

    List<Yachtsman> findAllByBoats_Id(Long boatId);
}
