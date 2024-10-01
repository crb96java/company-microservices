package com.microservices.company.entity;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String companyName;
    private String companyAddress;
    private String companyCity;
    private String companyState;
    private String companyCountry;
    private String companyZip;
    private String companyPhone;
    private String companyEmail;
}
