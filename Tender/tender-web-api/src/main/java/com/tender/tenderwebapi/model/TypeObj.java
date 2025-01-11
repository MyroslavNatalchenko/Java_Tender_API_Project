package com.tender.tenderwebapi.model;

public record TypeObj(long id,

        long tender_src_id,
        String sourceId,
        String name,
        String slug) {
}
