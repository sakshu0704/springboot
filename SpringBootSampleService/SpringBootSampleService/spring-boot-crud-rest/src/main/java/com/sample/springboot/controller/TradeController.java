package com.sample.springboot.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.springboot.entity.Trade;
import com.sample.springboot.exception.ResourceNotFoundException;
import com.sample.springboot.service.TradeService;

@RestController
@RequestMapping("/api/v1")
public class TradeController {
	
	@Autowired
	private TradeService tradeService;

	
	@GetMapping("/trade")
	public List<Trade> getAllTrades() {
		return tradeService.getAllTrade();
	}

	@GetMapping("/trade/{id}")
	public ResponseEntity<Trade> getTradeById(
			@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		Trade user = tradeService.getTradeById(userId);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/entertrade")
	public Trade enterTrade(@Valid @RequestBody Trade user) {
		return tradeService.saveTrade(user);
	}

	@PutMapping("/updatetrade/{id}")
	public ResponseEntity<Trade> updateTrade(
			@PathVariable(value = "id") Long userId,
			@Valid @RequestBody Trade userDetails) throws ResourceNotFoundException {
		Trade user = tradeService.updateUser(userId,userDetails);
		return ResponseEntity.ok(user);
	}

	@DeleteMapping("/removetrade/{id}")
	public Map<String, Boolean> deleteTrade(
			@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		Map<String, Boolean> response = tradeService.deleteUser(userId);
		return response;
	}
}
