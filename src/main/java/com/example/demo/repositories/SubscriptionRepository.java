package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Subscription;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

}