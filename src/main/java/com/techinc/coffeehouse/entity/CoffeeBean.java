package com.techinc.coffeehouse.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "coffee_bean")
public class CoffeeBean implements Serializable {
    private static final Long serialVersionUUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COFFEE_BEAN_ID")
    private int coffeeBeanId;
    @Column(name = "NAME", nullable = false, unique = true, length = 50)
    private String name;
    @Column(name = "SORT", nullable = false, length = 50)
    private String sort;
    @Column(name = "COUNTRY", nullable = false, length = 100)
    private String country;

    public CoffeeBean() {

    }

    public int getCoffeeBeanId() {
        return coffeeBeanId;
    }

    public void setCoffeeBeanId(int coffeeBeanId) {
        this.coffeeBeanId = coffeeBeanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }

        if (otherObj == null || getClass() != otherObj.getClass()) {
            return false;
        }

        CoffeeBean other = (CoffeeBean) otherObj;
        return coffeeBeanId == other.getCoffeeBeanId() &&
                Objects.equals(name, other.getName()) &&
                Objects.equals(sort, other.getSort()) &&
                Objects.equals(country, other.getCountry());
    }

    @Override
    public int hashCode() {
        return Objects.hash(coffeeBeanId, name, sort, country);
    }

    @Override
    public String toString() {
        return String.format("CoffeeBean: name %s, country %s", name, country);
    }
}
