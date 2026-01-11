package com.example.demo.repository;

import com.example.demo.data.EventToSubscribe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventToSubscribeRepository extends CrudRepository<EventToSubscribe, Long> {
    @Override
    List<EventToSubscribe> findAll();
}
