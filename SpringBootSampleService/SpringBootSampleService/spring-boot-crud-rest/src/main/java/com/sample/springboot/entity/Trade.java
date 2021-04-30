package com.sample.springboot.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "trade")
@EntityListeners(AuditingEntityListener.class)
public class Trade {

	private long id;
	private String clearingMember;
	private String tradingMember;
	private long tradePrice;
	private Date tradeDate;
	private int tradeQuantity;
	private String accountNumber;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "clearingMember", nullable = false)
	public String getclearingMember() {
		return clearingMember;
	}
	public void setclearingMember(String clearingMember) {
		this.clearingMember = clearingMember;
	}
	
	@Column(name = "tradingMember", nullable = false)
	public String gettradingMember() {
		return tradingMember;
	}
	public void settradingMember(String tradingMember) {
		this.tradingMember = tradingMember;
	}
	
	@Column(name = "tradePrice", nullable = false)
	public long gettradePrice() {
		return tradePrice;
	}
	public void settradePrice(long tradePrice) {
		this.tradePrice = tradePrice;
	}
	
	@Column(name = "tradeDate", nullable = false)
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	public Date gettradeDate() {
		return tradeDate;
	}
	public void settradeDate(Date tradeDate) {
		this.tradeDate = tradeDate;
	}
	
	@Column(name = "tradeQuantity", nullable = false)
	@CreatedBy
	public int gettradeQuantity() {
		return tradeQuantity;
	}
	public void settradeQuantity(int tradeQuantity) {
		this.tradeQuantity = tradeQuantity;
	}
	
	
	
	@Column(name = "accountNumber", nullable = false)
	@LastModifiedBy
	public String getaccountNumber() {
		return accountNumber;
	}
	public void setaccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
}
