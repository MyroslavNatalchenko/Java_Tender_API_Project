package com.tender.tenderwebapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

public record TenderObj (
    long id,
     int sourceId,
     String date,
     String deadlineDate,
     String deadlineLengthDays,
     String title,
     String category,
     String sid,
     String sourceUrl){
    public TenderObj(long id, int sourceId, String date, String deadlineDate, String deadlineLengthDays, String title, String category, String sid, String sourceUrl) {
        this.id = id;
        this.sourceId = sourceId;
        this.date = date;
        this.deadlineDate = deadlineDate;
        this.deadlineLengthDays = deadlineLengthDays;
        this.title = title;
        this.category = category;
        this.sid = sid;
        this.sourceUrl = sourceUrl;
    }
}
