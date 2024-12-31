package com.tender.tenderclient.client.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record TenderDto(
        long id,
        String date,
        @JsonProperty("deadline_date") String deadlineDate,
        @JsonProperty("deadline_length_days") String deadlineLengthDays,
        String title,
        String category,
        String sid,
        @JsonProperty("src_url") String sourceUrl,
        PurchaserDto purchaser,
        TypeDto type,
        List<AwardDto> awarded
) {}
