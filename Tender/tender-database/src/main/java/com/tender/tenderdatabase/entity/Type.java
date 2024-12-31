package com.tender.tenderdatabase.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long tender_src_id;
    private String sourceId;
    private String name;
    private String slug;

    @OneToOne
    @JoinColumn(name = "tender_id") // Customize the column name
    private Tender tender;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Type type = (Type) o;
        return id == type.id && tender_src_id == type.tender_src_id && Objects.equals(sourceId, type.sourceId) && Objects.equals(name, type.name) && Objects.equals(slug, type.slug) && Objects.equals(tender, type.tender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tender_src_id, sourceId, name, slug, tender);
    }
}
