package com.sample.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.sample.springboot.entity.Trade;

@Repository
public interface TradeRepository extends JpaRepository<Trade, Long>{

}
