package com.github.aistomin.jhipster.sandbox.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.github.aistomin.jhipster.sandbox.domain.MyFirstEntity} entity.
 */
public class MyFirstEntityDTO implements Serializable {
    
    private Long id;

    private String stringField;

    private LocalDate dateCreated;

    private Integer someNumber;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getSomeNumber() {
        return someNumber;
    }

    public void setSomeNumber(Integer someNumber) {
        this.someNumber = someNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MyFirstEntityDTO myFirstEntityDTO = (MyFirstEntityDTO) o;
        if (myFirstEntityDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), myFirstEntityDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MyFirstEntityDTO{" +
            "id=" + getId() +
            ", stringField='" + getStringField() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", someNumber=" + getSomeNumber() +
            "}";
    }
}
