package com.tender.tenderdatabase.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//public record TenderDto(
//        long id,
//        String date,
//        @JsonProperty("deadline_date") String deadlineDate,
//        @JsonProperty("deadline_length_days") String deadlineLengthDays,
//        String title,
//        String category,
//        String sid,
//        @JsonProperty("src_url") String sourceUrl,
//        PurchaserDto purchaser,
//        TypeDto type,
//        List<AwardDto> awarded
//) {}


@Entity
public class Tender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int sourceId;
    private String date;
    private String deadlineDate;
    private String deadlineLengthDays;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String title;
    private String category;
    private String sid;
    private String sourceUrl;
    @OneToOne(mappedBy = "tender")
    private Purchaser purchaser;

    @OneToOne(mappedBy = "tender")
    private Type type;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(String deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getDeadlineLengthDays() {
        return deadlineLengthDays;
    }

    public void setDeadlineLengthDays(String deadlineLengthDays) {
        this.deadlineLengthDays = deadlineLengthDays;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public Purchaser getPurchaser() {
        return purchaser;
    }

    public void setPurchaser(Purchaser purchaser) {
        this.purchaser = purchaser;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tender tender = (Tender) o;
        return id == tender.id && sourceId == tender.sourceId && Objects.equals(date, tender.date) && Objects.equals(deadlineDate, tender.deadlineDate) && Objects.equals(deadlineLengthDays, tender.deadlineLengthDays) && Objects.equals(title, tender.title) && Objects.equals(category, tender.category) && Objects.equals(sid, tender.sid) && Objects.equals(sourceUrl, tender.sourceUrl) && Objects.equals(purchaser, tender.purchaser) && Objects.equals(type, tender.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sourceId, date, deadlineDate, deadlineLengthDays, title, category, sid, sourceUrl, purchaser, type);
    }
}
