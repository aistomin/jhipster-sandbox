package com.github.aistomin.jhipster.sandbox.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.Criteria;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.LocalDateFilter;

/**
 * Criteria class for the {@link com.github.aistomin.jhipster.sandbox.domain.MyFirstEntity} entity. This class is used
 * in {@link com.github.aistomin.jhipster.sandbox.web.rest.MyFirstEntityResource} to receive all the possible filtering options from
 * the Http GET request parameters.
 * For example the following could be a valid request:
 * {@code /my-first-entities?id.greaterThan=5&attr1.contains=something&attr2.specified=false}
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MyFirstEntityCriteria implements Serializable, Criteria {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter stringField;

    private LocalDateFilter dateCreated;

    private IntegerFilter someNumber;

    public MyFirstEntityCriteria() {
    }

    public MyFirstEntityCriteria(MyFirstEntityCriteria other) {
        this.id = other.id == null ? null : other.id.copy();
        this.stringField = other.stringField == null ? null : other.stringField.copy();
        this.dateCreated = other.dateCreated == null ? null : other.dateCreated.copy();
        this.someNumber = other.someNumber == null ? null : other.someNumber.copy();
    }

    @Override
    public MyFirstEntityCriteria copy() {
        return new MyFirstEntityCriteria(this);
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getStringField() {
        return stringField;
    }

    public void setStringField(StringFilter stringField) {
        this.stringField = stringField;
    }

    public LocalDateFilter getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateFilter dateCreated) {
        this.dateCreated = dateCreated;
    }

    public IntegerFilter getSomeNumber() {
        return someNumber;
    }

    public void setSomeNumber(IntegerFilter someNumber) {
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
        final MyFirstEntityCriteria that = (MyFirstEntityCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(stringField, that.stringField) &&
            Objects.equals(dateCreated, that.dateCreated) &&
            Objects.equals(someNumber, that.someNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        stringField,
        dateCreated,
        someNumber
        );
    }

    @Override
    public String toString() {
        return "MyFirstEntityCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (stringField != null ? "stringField=" + stringField + ", " : "") +
                (dateCreated != null ? "dateCreated=" + dateCreated + ", " : "") +
                (someNumber != null ? "someNumber=" + someNumber + ", " : "") +
            "}";
    }

}
