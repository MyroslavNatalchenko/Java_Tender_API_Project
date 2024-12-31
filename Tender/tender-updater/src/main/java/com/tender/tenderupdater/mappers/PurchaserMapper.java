package com.tender.tenderupdater.mappers;

import com.tender.tenderclient.client.data.PurchaserDto;
import com.tender.tenderdatabase.entity.Purchaser;
import org.hibernate.annotations.CollectionTypeRegistration;
import org.springframework.stereotype.Component;

@Component
public class PurchaserMapper implements IMapEntities<PurchaserDto, Purchaser> {
    @Override
    public Purchaser map(PurchaserDto purchaserDto) {
        return map(purchaserDto, new Purchaser());
    }

    @Override
    public Purchaser map(PurchaserDto purchaserDto, Purchaser purchaser) {
        purchaser.setSourceId((int)purchaserDto.id());
        purchaser.setName(purchaserDto.name());
        purchaser.setSid(purchaserDto.sid());
        purchaser.setTender_src_id(purchaserDto.tender_src_id());
        return purchaser;
    }
}
