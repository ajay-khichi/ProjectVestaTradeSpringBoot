package com.ignishers.pojo;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;



@Entity
@Table(name = "stock_price_history")
public class StockPriceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long stockId;                // FK → stocks

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;            // price at that moment

    @Column(nullable = false, updatable = false)
    private LocalDateTime recordedAt;    // when Admin changed the price

    @PrePersist
    protected void onCreate() {
        this.recordedAt = LocalDateTime.now();
    }

	public StockPriceHistory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StockPriceHistory(Long id, Long stockId, BigDecimal price, LocalDateTime recordedAt) {
		super();
		this.id = id;
		this.stockId = stockId;
		this.price = price;
		this.recordedAt = recordedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStockId() {
		return stockId;
	}

	public void setStockId(Long stockId) {
		this.stockId = stockId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDateTime getRecordedAt() {
		return recordedAt;
	}

	public void setRecordedAt(LocalDateTime recordedAt) {
		this.recordedAt = recordedAt;
	}
    
}