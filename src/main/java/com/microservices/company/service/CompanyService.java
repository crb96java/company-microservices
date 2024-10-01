package com.microservices.company.service;

import com.microservices.company.bean.CompanyDetails;
import com.microservices.company.entity.Company;

public interface CompanyService {
    void saveCompanyDetails(CompanyDetails companyDetails);
}
