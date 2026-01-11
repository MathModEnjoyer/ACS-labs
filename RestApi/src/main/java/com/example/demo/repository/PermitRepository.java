package com.example.demo.repository;

import com.example.demo.data.Permit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermitRepository extends CrudRepository<Permit, Long> {
    Permit findFirstById(Long id);
    @Override
    List<Permit> findAll();
    List<Permit> findAllByBoat_Id(Long boatId);
}
