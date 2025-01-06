package com.tender.tenderwebapi.model;

public record SupplierObj (long id,

        long source_id,
        String name,
        String slug){
    public SupplierObj(long id, long source_id, String name, String slug) {
        this.id = id;
        this.source_id = source_id;
        this.name = name;
        this.slug = slug;
    }
}
