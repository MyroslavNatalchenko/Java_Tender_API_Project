package com.tender.tenderdatabase.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long source_id;
    private String name;
    private String slug;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Awarded> awardedList=new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSource_id() {
        return source_id;
    }

    public void setSource_id(long source_id) {
        this.source_id = source_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public List<Awarded> getAwardedList() {
        return awardedList;
    }

    public void setAwardedList(List<Awarded> awardedList) {
        this.awardedList = awardedList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Supplier supplier = (Supplier) o;
        return id == supplier.id && source_id == supplier.source_id && Objects.equals(name, supplier.name) && Objects.equals(slug, supplier.slug) && Objects.equals(awardedList, supplier.awardedList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, source_id, name, slug, awardedList);
    }
}
