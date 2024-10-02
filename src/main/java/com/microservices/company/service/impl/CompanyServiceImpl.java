package com.microservices.company.service.impl;

import com.microservices.company.exceptions.CompanyNotFoundException;
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
        Company company = new Company();
        log.info("CompanyServiceImpl::saveCompanyDetails request received with {}", companyDetails);

        //check the condition with companyDetails object is present or not
        if (Optional.ofNullable(companyDetails).isPresent()) {
            String mandatoryFieldsValidation = checkMandatoryFieldsValidation(companyDetails);
            company = getCompanyDetails(companyDetails);

            //save the company details
            companyRepository.save(company);
            log.info("CompanyServiceImpl::saveCompanyDetails Company data successfully saved with {}", company);
        } else {
            log.info("CompanyServiceImpl:saveCompanyDetails we are not able to save the Company details with {}", company);
        }

    }

    /**
     * Get Company details by company name and company email
     *
     * @param companyName companyEmail
     *                    Get company details by companyName and companyEmail
     * @return companyDetails
     * Get companyDetails Object
     */
    @Override
    public CompanyDetails getCompanyDetailsByNameAndEmail(final String companyName, final String companyEmail) throws CompanyNotFoundException {

        CompanyDetails companyDetails = new CompanyDetails();

        //check the condition with companyName and companyEmail id is present or not
        if (companyName.isEmpty() && companyEmail.isEmpty()) {
            throw new CompanyNotFoundException(Constants.COMPANYNAMEANDEMAILMISSING + Constants.COMPANYNAME + Constants.COMPANYEMAIL);
        }

        //get the company details
        Optional<Company> company = this.companyRepository.findByCompanyNameAndCompanyEmail(companyName, companyEmail);
        if (company.isPresent()) {
            companyDetails = getCompanyDetails(company);
        } else {
            log.info("CompanyServiceImpl:getCompanyDetailsByNameAndEmail company details are not finding with {} : {}", Constants.COMPANYNAME, Constants.COMPANYEMAIL);
        }
        return companyDetails;
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
    private static Company getCompanyDetails(CompanyDetails companyDetails) {
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

    /**
     * Create the Company object from company details
     *
     * @param company - Company
     * @return CompanyDetails object
     */
    private static CompanyDetails getCompanyDetails(Optional<Company> company) {
        CompanyDetails companyDetails = new CompanyDetails();
        companyDetails.setCompanyRegistrationId(company.get().getId());
        companyDetails.setCompanyName(company.get().getCompanyName());
        companyDetails.setCompanyAddress(company.get().getCompanyAddress());
        companyDetails.setCompanyCity(company.get().getCompanyCity());
        companyDetails.setCompanyState(company.get().getCompanyState());
        companyDetails.setCompanyCountry(company.get().getCompanyCountry());
        companyDetails.setCompanyZip(company.get().getCompanyZip());
        companyDetails.setCompanyEmail(company.get().getCompanyEmail());
        companyDetails.setCompanyPhone(company.get().getCompanyPhone());
        return companyDetails;
    }
}
