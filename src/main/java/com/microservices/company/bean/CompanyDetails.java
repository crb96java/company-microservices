package com.microservices.company.bean;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyDetails implements Serializable {

    private String companyName;
    private String companyAddress;
    private String companyCity;
    private String companyState;
    private String companyCountry;
    private String companyZip;
    private String companyPhone;
    private String companyEmail;


}
