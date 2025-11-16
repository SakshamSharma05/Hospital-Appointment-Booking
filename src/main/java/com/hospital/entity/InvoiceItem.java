package com.hospital.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "invoice_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    @NotNull(message = "Invoice is required")
    private Invoice invoice;
    
    @Column(name = "item_description", nullable = false, length = 255)
    @NotBlank(message = "Item description is required")
    private String itemDescription;
    
    @Column(nullable = false)
    private Integer quantity = 1;
    
    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Unit price is required")
    private BigDecimal unitPrice;
    
    @Column(name = "total_price", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "Total price is required")
    private BigDecimal totalPrice;
    
    @PrePersist
    @PreUpdate
    protected void calculateTotal() {
        if (unitPrice != null && quantity != null) {
            totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
        }
    }
}

