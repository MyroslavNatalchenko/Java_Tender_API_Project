package com.tender.tenderclient.client.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public record AwardDto(
        long tender_srs_id,
        String date,
        @JsonProperty("value_for_one") double valueForOne,
        @JsonProperty("value_for_two") double valueForTwo,
        @JsonProperty("value_for_three") double valueForThree,
        @JsonProperty("suppliers_id") long suppliersId, //STRING
        String count,
        @JsonProperty("offers_count") int offersCount,
        String value,
        List<SupplierDto> suppliers
        // @JsonProperty("suppliers_name") String suppliersName,
        // @JsonProperty("offers_count_data") Map<String, OfferDetailDto> offersCountData
) {
    public AwardDto(long tender_srs_id, String date, @JsonProperty("value_for_one") double valueForOne, @JsonProperty("value_for_two") double valueForTwo, @JsonProperty("value_for_three") double valueForThree, @JsonProperty("suppliers_id") long suppliersId, String count, @JsonProperty("offers_count") int offersCount, String value, List<SupplierDto> suppliers) {
        this.tender_srs_id = tender_srs_id;
        this.date = date;
        this.valueForOne = valueForOne;
        this.valueForTwo = valueForTwo;
        this.valueForThree = valueForThree;
        this.suppliersId = suppliersId;
        this.count = count;
        this.offersCount = offersCount;
        this.value = value;
        this.suppliers = suppliers;
    }

    public record OfferDetailDto(
            int count,
            String value
    ) {}
}
