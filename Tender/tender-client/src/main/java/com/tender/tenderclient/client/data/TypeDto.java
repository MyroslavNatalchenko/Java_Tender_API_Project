package com.tender.tenderclient.client.data;

public record TypeDto(
        long tender_src_id,
        String id, //bylo string
        String name,
        String slug
) {
    public TypeDto(long tender_src_id, String id, String name, String slug) {
        this.tender_src_id = tender_src_id;
        this.id = id;
        this.name = name;
        this.slug = slug;
    }
}

