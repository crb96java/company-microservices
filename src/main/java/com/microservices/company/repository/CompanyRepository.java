package com.microservices.company.repository;

import com.microservices.company.entity.Company;
import com.microservices.company.model.CompanyDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByCompanyNameAndCompanyEmail(String companyName, String companyEmail);
}
