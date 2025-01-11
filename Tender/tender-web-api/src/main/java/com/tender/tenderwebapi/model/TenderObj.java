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
}
