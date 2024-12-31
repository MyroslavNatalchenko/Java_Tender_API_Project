package com.tender.tenderclient.client.data;

public record PurchaserDto(
        long tender_src_id,
        long id,
        String sid,
        String name
) {
    public PurchaserDto(long tender_src_id, long id, String sid, String name) {
        this.tender_src_id = tender_src_id;
        this.id = id;
        this.sid = sid;
        this.name = name;
    }
}

