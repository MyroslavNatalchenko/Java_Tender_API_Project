package com.tender.tenderupdater.mappers;

import com.tender.tenderclient.client.data.AwardDto;
import com.tender.tenderdatabase.entity.Awarded;
import org.springframework.stereotype.Component;

@Component
public class AwardedMapper implements IMapEntities<AwardDto, Awarded> {
    @Override
    public Awarded map(AwardDto awardDto) {
        return map(awardDto, new Awarded());
    }

    @Override
    public Awarded map(AwardDto awardDto, Awarded awarded) {
        awarded.setCount(awardDto.count());
        awarded.setDate(awardDto.date());
        awarded.setOffersCount(awardDto.offersCount());
        awarded.setTender_src_id(awardDto.tender_srs_id());
        awarded.setValue(awardDto.value());
        awarded.setValueForOne(awardDto.valueForOne());
        awarded.setValueForTwo(awardDto.valueForTwo());
        awarded.setValueForThree(awardDto.valueForThree());
        awarded.setSuppliersId(awardDto.suppliersId());
        return awarded;
    }
}
