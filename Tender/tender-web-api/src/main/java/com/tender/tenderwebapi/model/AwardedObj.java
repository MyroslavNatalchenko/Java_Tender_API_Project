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
}
