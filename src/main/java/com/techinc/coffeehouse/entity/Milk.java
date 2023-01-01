package com.techinc.coffeehouse.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "milk")
public class Milk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MILK_ID")
    private int milkId;
    @Column(name = "NAME", length = 40, nullable = false, unique = true)
    private String name;
    @Column(name = "PRODUCER", length = 50, nullable = false)
    private String producer;
    @Column(name = "FAT_CONTENT", nullable = false)
    private Double fatContentPercentage;

    public Milk() {

    }

    public int getMilkId() {
        return milkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Double getFatContentPercentage() {
        return fatContentPercentage;
    }

    public void setFatContentPercentage(Double fatContentPercentage) {
        this.fatContentPercentage = fatContentPercentage;
    }

    @Override
    public boolean equals(Object otherObj) {
        if (this == otherObj) {
            return true;
        }

        if (otherObj == null || getClass() != otherObj.getClass()) {
            return false;
        }

        Milk other = (Milk) otherObj;
        return milkId == other.getMilkId() &&
                Objects.equals(name, other.getName()) &&
                Objects.equals(producer, other.getProducer()) &&
                Double.compare(fatContentPercentage, other.getFatContentPercentage()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(milkId, name, producer, fatContentPercentage);
    }

    @Override
    public String toString() {
        return String.format("Milk %s which is produced by %s, %f fat content", name, producer, fatContentPercentage);
    }
}
