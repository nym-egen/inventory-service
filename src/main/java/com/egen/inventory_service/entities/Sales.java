package com.egen.inventory_service.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

//@Getter
//@Setter
@Entity
@Table(name = "sales")
public class Sales {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "sales_date",nullable = false)
    private LocalDate salesDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "customer_id",insertable = false, updatable = false)
    private Long customerId;

    @Column(name = "due_amount")
    private Double dueAmount;

    @OneToMany(mappedBy = "sales")
    private List<SalesItem> salesItemList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(LocalDate salesDate) {
        this.salesDate = salesDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Double getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(Double dueAmount) {
        this.dueAmount = dueAmount;
    }

    public List<SalesItem> getSalesItemList() {
        return salesItemList;
    }

    public void setSalesItemList(List<SalesItem> salesItemList) {
        this.salesItemList = salesItemList;
    }
}
