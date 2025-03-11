package com.egen.inventory_service.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.List;

//@Getter
//@Setter
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "c_name", nullable = false)
    private String name;

    @Column(name = "c_mobile", unique = true, nullable = false, length = 14)
    private String mobile;

    @Column(name = "c_email", unique = true, nullable = false, length = 120)
    @Size(min = 10, max = 120)
    private String email;

    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dob;

    @OneToMany(mappedBy = "customer")
    private List<Sales> salesList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public @Size(min = 10, max = 120) String getEmail() {
        return email;
    }

    public void setEmail(@Size(min = 10, max = 120) String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public List<Sales> getSalesList() {
        return salesList;
    }

    public void setSalesList(List<Sales> salesList) {
        this.salesList = salesList;
    }
}
