package com.tender.tenderdatabase.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Awarded {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "tender_id")
    private Tender tender;
    private long tender_src_id;
    private String date;
    private double valueForOne;
    private double valueForTwo;
    private double valueForThree;
    private long suppliersId;
    private String count;
    private int offersCount;
    String value;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Tender getTender() {
        return tender;
    }

    public void setTender(Tender tender) {
        this.tender = tender;
    }

    public long getTender_src_id() {
        return tender_src_id;
    }

    public void setTender_src_id(long tender_src_id) {
        this.tender_src_id = tender_src_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValueForOne() {
        return valueForOne;
    }

    public void setValueForOne(double valueForOne) {
        this.valueForOne = valueForOne;
    }

    public double getValueForTwo() {
        return valueForTwo;
    }

    public void setValueForTwo(double valueForTwo) {
        this.valueForTwo = valueForTwo;
    }

    public double getValueForThree() {
        return valueForThree;
    }

    public void setValueForThree(double valueForThree) {
        this.valueForThree = valueForThree;
    }

    public long getSuppliersId() {
        return suppliersId;
    }

    public void setSuppliersId(long suppliersId) {
        this.suppliersId = suppliersId;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getOffersCount() {
        return offersCount;
    }

    public void setOffersCount(int offersCount) {
        this.offersCount = offersCount;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Awarded awarded = (Awarded) o;
        return id == awarded.id && tender_src_id == awarded.tender_src_id && Double.compare(valueForOne, awarded.valueForOne) == 0 && Double.compare(valueForTwo, awarded.valueForTwo) == 0 && Double.compare(valueForThree, awarded.valueForThree) == 0 && suppliersId == awarded.suppliersId && offersCount == awarded.offersCount && Objects.equals(tender, awarded.tender) && Objects.equals(date, awarded.date) && Objects.equals(count, awarded.count) && Objects.equals(value, awarded.value) && Objects.equals(supplier, awarded.supplier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tender, tender_src_id, date, valueForOne, valueForTwo, valueForThree, suppliersId, count, offersCount, value, supplier);
    }
}
