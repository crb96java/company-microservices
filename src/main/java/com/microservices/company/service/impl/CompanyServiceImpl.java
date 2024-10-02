package com.microservices.company.service.impl;

import com.microservices.company.model.CompanyDetails;
import com.microservices.company.entity.Company;
import com.microservices.company.exceptions.MandatoryFieldsValidationException;
import com.microservices.company.repository.CompanyRepository;
import com.microservices.company.service.CompanyService;
import com.microservices.company.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    /**
     * CompanyRepository
     */
    private CompanyRepository companyRepository;

    @Autowired
    public void setCompanyRepository(@Qualifier("companyRepository") CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    /**
     * Request POST call to create Company Details
     *
     * @param companyDetails the company to create
     */
    @Override
    public void saveCompanyDetails(CompanyDetails companyDetails) {
        Company company = null;
        log.info("CompanyServiceImpl::saveCompanyDetails request received with {}", companyDetails);
        if (Optional.ofNullable(companyDetails).isPresent()) {
            String mandatoryFieldsValidation = checkMandatoryFieldsValidation(companyDetails);
            company = getCompanyDetails(companyDetails);
            companyRepository.save(company);
            log.info("CompanyServiceImpl::saveCompanyDetails Company data successfully saved with {}", company);
        } else {
            log.info("CompanyServiceImpl:saveCompanyDetails we are not able to save the Company details with {}", company);
        }

    }

    /**
     * Check the mandatory fields validation
     *
     * @param companyDetails to validate the company email it
     * @return String value
     */
    private String checkMandatoryFieldsValidation(CompanyDetails companyDetails) {
        return Optional.ofNullable(companyDetails.getCompanyEmail()).orElseThrow(() -> new MandatoryFieldsValidationException(Constants.MANDATORYFIELDSMISSING));
    }

    /**
     * Create the Company object from company details
     *
     * @param companyDetails - Company
     * @return Company object
     */
    private Company getCompanyDetails(CompanyDetails companyDetails) {
        Company company = new Company();
        company.setCompanyName(companyDetails.getCompanyName());
        company.setCompanyAddress(companyDetails.getCompanyAddress());
        company.setCompanyCity(companyDetails.getCompanyCity());
        company.setCompanyState(companyDetails.getCompanyState());
        company.setCompanyCountry(companyDetails.getCompanyCountry());
        company.setCompanyZip(companyDetails.getCompanyZip());
        company.setCompanyEmail(companyDetails.getCompanyEmail());
        company.setCompanyPhone(companyDetails.getCompanyPhone());
        return company;
    }
}
