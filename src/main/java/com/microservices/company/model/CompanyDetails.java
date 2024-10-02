package com.microservices.company.model;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({
        "companyType",
        "companyLocalAddress"
})
@JsonPropertyOrder({
        "company_name",
        "company_email",
        "company_phone",
        "company_address"
})
public class CompanyDetails implements Serializable {

    @JsonIgnore
    private String id;

    @JsonProperty("company_name")
    @JsonPropertyDescription("Company details")
    private String companyName;

    @JsonProperty("company_address")
    @JsonPropertyDescription("Company address details")
    private String companyAddress;

    @JsonProperty("company_city")
    @JsonPropertyDescription("Company city details")
    private String companyCity;

    @JsonProperty(value = "company_state", access = JsonProperty.Access.WRITE_ONLY)
    @JsonPropertyDescription("Company status for only reading per poss ")
    private String companyState;

    @JsonProperty("company_country")
    @JsonPropertyDescription("Company country details")
    private String companyCountry;

    @JsonProperty("company_zip")
    @JsonPropertyDescription("Company Zip code details")
    private String companyZip;

    @JsonProperty("company_phone")
    @JsonPropertyDescription("Company phone number details")
    private String companyPhone;

    @JsonProperty("company_email")
    @JsonPropertyDescription("Company email details")
    private String companyEmail;

    @JsonProperty(value = "company_code", access = JsonProperty.Access.READ_ONLY)
    @JsonPropertyDescription("Company code details")
    private String companyCode;

    /*@JsonRawValue
    @JsonPropertyDescription("Company details")
    @JsonProperty(value = "company_details", access = JsonProperty.Access.READ_ONLY)
    private String companyDetails;*/

    private String companyType;
    private String companyLocalAddress;

}
