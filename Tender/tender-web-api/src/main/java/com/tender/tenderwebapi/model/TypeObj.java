package com.tender.tenderwebapi.model;

public record TypeObj(long id,

        long tender_src_id,
        String sourceId,
        String name,
        String slug) {
    public TypeObj(long id, long tender_src_id, String sourceId, String name, String slug) {
        this.id = id;
        this.tender_src_id = tender_src_id;
        this.sourceId = sourceId;
        this.name = name;
        this.slug = slug;
    }
}
