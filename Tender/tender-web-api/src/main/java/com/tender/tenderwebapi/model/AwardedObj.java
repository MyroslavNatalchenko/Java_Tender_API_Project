package com.tender.tenderwebapi.model;

public record AwardedObj(long id,
                         long tender_src_id,
                         String date,
                         double valueForOne,
                         double valueForTwo,
                         double valueForThree,
                         long suppliersId,
                         String count,
                         int offersCount,
                         String value) {
    public AwardedObj(long id, long tender_src_id, String date, double valueForOne, double valueForTwo, double valueForThree, long suppliersId, String count, int offersCount, String value) {
        this.id = id;
        this.tender_src_id = tender_src_id;
        this.date = date;
        this.valueForOne = valueForOne;
        this.valueForTwo = valueForTwo;
        this.valueForThree = valueForThree;
        this.suppliersId = suppliersId;
        this.count = count;
        this.offersCount = offersCount;
        this.value = value;
    }
}
