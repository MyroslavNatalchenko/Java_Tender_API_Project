package com.tender.tenderwebapi.model;

public record PurchaserObj(long id,
                           long tender_src_id,
                           int sourceId,
                           String sid,
                           String name) {
    public PurchaserObj(long id, long tender_src_id, int sourceId, String sid, String name) {
        this.id = id;
        this.tender_src_id = tender_src_id;
        this.sourceId = sourceId;
        this.sid = sid;
        this.name = name;
    }
}
