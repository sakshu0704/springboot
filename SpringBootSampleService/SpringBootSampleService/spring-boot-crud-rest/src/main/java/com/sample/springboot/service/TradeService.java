package com.sample.springboot.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.springboot.entity.Trade;
import com.sample.springboot.exception.ResourceNotFoundException;
import com.sample.springboot.repository.TradeRepository;

@Service
public class TradeService {

	@Autowired
	private TradeRepository tradeRepository;
	
	@Transactional
	public List<Trade> getAllTrade() {
		List<Trade> trades = new ArrayList<>(); 
		tradeRepository.findAll()
		.forEach(trades::add);
		return trades;
	}

	@Transactional
	public Trade getTradeById(Long userId) throws ResourceNotFoundException {
		Trade user = tradeRepository.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
		return user;
	}

	@Transactional
	public Trade saveTrade(Trade user) {
		return tradeRepository.save(user);
	}

	@Transactional
	public Trade updateUser(Long userId, Trade userDetails) throws ResourceNotFoundException {
		Trade user = tradeRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
		
		user.settradingMember(userDetails.gettradingMember());
		user.setclearingMember(userDetails.getclearingMember());
		user.settradeDate(new Date());
		final Trade updatedUser =tradeRepository.save(user);
		return updatedUser;
	}

	@Transactional
	public Map<String, Boolean> deleteUser(Long userId) throws ResourceNotFoundException {
		Trade user = tradeRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found :: " + userId));
		tradeRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}


}
