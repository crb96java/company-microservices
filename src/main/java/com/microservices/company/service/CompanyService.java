package com.microservices.company.service;

import com.microservices.company.exceptions.CompanyNotFoundException;
import com.microservices.company.model.CompanyDetails;

public interface CompanyService {
    void saveCompanyDetails(CompanyDetails companyDetails);

    CompanyDetails getCompanyDetailsByNameAndEmail(String companyName, String companyEmail) throws CompanyNotFoundException;
}
