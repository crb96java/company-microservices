package com.microservices.company.controller;

import com.microservices.company.bean.CompanyDetails;
import com.microservices.company.entity.Company;
import com.microservices.company.service.CompanyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class CompanyController {

    /**
     * CompanyService
     * */
    private CompanyService companyService;

    @Autowired
    public void setCompanyService(@Qualifier("companyService") CompanyService companyService) {
        this.companyService = companyService;
    }

    /**
     * Request POST call to create Company Details
     * @param companyDetails
     *      - the company to create
     * @return no return type
     */
    @PostMapping("/save/company-details")
    public ResponseEntity<Void> saveCompanyDetails (@RequestBody final CompanyDetails companyDetails) {
        log.info("CompanyController::saveCompanyDetails request received with {}", companyDetails);
        companyService.saveCompanyDetails(companyDetails);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
