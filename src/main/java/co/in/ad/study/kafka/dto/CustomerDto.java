package co.in.ad.study.kafka.dto;

import lombok.Data;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class CustomerDto implements Serializable {
    private long customerId;
    private String firstName;
    private String lastName;
    private String dob;
    private String email;
    private String phone;
    private String country_code;
    private String buildingNumber;
    private String street;
    private String pinCode;
    private String city;
    private String district;
    private String state;
    private String countryName;
}
