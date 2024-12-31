package com.tender.tenderdatabase.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Purchaser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long tender_src_id;

    private int sourceId;
    private String sid;
    private String name;

    @OneToOne
    @JoinColumn(name = "tender_id") // Customize the column name
    private Tender tender;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSourceId() {
        return sourceId;
    }

    public void setSourceId(int sourceId) {
        this.sourceId = sourceId;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Purchaser purchaser = (Purchaser) o;
        return id == purchaser.id && tender_src_id == purchaser.tender_src_id && sourceId == purchaser.sourceId && Objects.equals(sid, purchaser.sid) && Objects.equals(name, purchaser.name) && Objects.equals(tender, purchaser.tender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tender_src_id, sourceId, sid, name, tender);
    }
}
