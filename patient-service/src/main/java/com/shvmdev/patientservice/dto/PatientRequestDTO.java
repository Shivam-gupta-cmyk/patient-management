package com.shvmdev.patientservice.dto;

import com.shvmdev.patientservice.dto.validators.CreatePatientValidationGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

public class PatientRequestDTO {

    @NotNull
    @Size(max=1000, message = "Name cannot exceed 100 characters")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message ="Email should be valid")
    private String email;

    @NotBlank(message ="Address is required")
    private String address;

    @NotBlank(message = "Date of Birth is required")
    private String dateOfBirth;

    @Setter
    @Getter
    @NotBlank(groups = CreatePatientValidationGroup.class, message = "Registered Date is required")
    private String registeredDate;

    public @NotNull @Size(max = 1000, message = "Name cannot exceed 100 characters") String getName() {
        return name;
    }

    public void setName(@NotNull @Size(max = 1000, message = "Name cannot exceed 100 characters") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email is required") @Email(message = "Email should be valid") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") @Email(message = "Email should be valid") String email) {
        this.email = email;
    }

    public @NotBlank(message = "Address is required") String getAddress() {
        return address;
    }

    public void setAddress(@NotBlank(message = "Address is required") String address) {
        this.address = address;
    }

    public @NotBlank(message = "Date of Birth is required") String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@NotBlank(message = "Date of Birth is required") String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
