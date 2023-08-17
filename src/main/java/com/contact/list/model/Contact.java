package com.contact.list.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "the name is required")
    private String name;

    @NotEmpty(message = "the email is required")
    @Email
    private String email;

    @NotBlank(message = "the phone is required")
    private String cellPhone;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past // permite solo fechas del pasada no futuras
    @NotNull(message = "the date is required")
    private LocalDate birthDate;

    private LocalDateTime createDate;

    public Contact() {
    }

    public Contact(Long id, String name, String email, String cellPhone, LocalDate birthDate, LocalDateTime createDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.cellPhone = cellPhone;
        this.birthDate = birthDate;
        this.createDate = createDate;
    }

    @PrePersist
    public void setCreatedDate() {
        createDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }
}
