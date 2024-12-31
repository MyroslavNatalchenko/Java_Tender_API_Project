package com.tender.tenderclient.client.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record PagedTenderDto(
        @JsonProperty("data") List<TenderDto> tenders,
        int page_number,
        int page_count,
        int total
) {}