package com.github.aistomin.jhipster.sandbox.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.LocalDate;

/**
 * A MyFirstEntity.
 */
@Entity
@Table(name = "my_first_entity")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MyFirstEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "string_field")
    private String stringField;

    @Column(name = "date_created")
    private LocalDate dateCreated;

    @Column(name = "some_number")
    private Integer someNumber;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStringField() {
        return stringField;
    }

    public MyFirstEntity stringField(String stringField) {
        this.stringField = stringField;
        return this;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public MyFirstEntity dateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
        return this;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Integer getSomeNumber() {
        return someNumber;
    }

    public MyFirstEntity someNumber(Integer someNumber) {
        this.someNumber = someNumber;
        return this;
    }

    public void setSomeNumber(Integer someNumber) {
        this.someNumber = someNumber;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MyFirstEntity)) {
            return false;
        }
        return id != null && id.equals(((MyFirstEntity) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "MyFirstEntity{" +
            "id=" + getId() +
            ", stringField='" + getStringField() + "'" +
            ", dateCreated='" + getDateCreated() + "'" +
            ", someNumber=" + getSomeNumber() +
            "}";
    }
}
