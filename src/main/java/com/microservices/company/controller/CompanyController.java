package com.microservices.company.controller;

import com.microservices.company.model.CompanyDetails;
import com.microservices.company.service.CompanyService;
import com.microservices.company.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    /**
     * Request POST call to create Company Details
     * @param companyDetails
     *      - the company to create
     * @return no return type
     */
    @GetMapping("/company/{companyName}/{companyEmail}")
    public ResponseEntity<CompanyDetails> getCompanyDetailsByCompanyNameAndCompanyEmail(@PathVariable final String companyName, @PathVariable final String companyEmail) {
        log.info("CompanyController::getCompanyDetailsByCompanyNameAndCompanyEmail request received with {} : {}", companyName, companyEmail);

        try{
            //Get the company details by company name and company email
            CompanyDetails companyDetails = companyService.getCompanyDetailsByNameAndEmail(companyName, companyEmail);
            return new ResponseEntity<>(companyDetails, HttpStatus.OK);
        }catch (Exception exception){
            log.error("CompanyController::getCompanyDetailsByCompanyNameAndCompanyEmail failing with Error message {}", exception.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
