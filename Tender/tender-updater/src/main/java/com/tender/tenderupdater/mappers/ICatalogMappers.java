package com.tender.tenderupdater.mappers;

import com.tender.tenderclient.client.data.AwardDto;
import com.tender.tenderclient.client.data.PurchaserDto;
import com.tender.tenderclient.client.data.TenderDto;
import com.tender.tenderclient.client.data.TypeDto;
import com.tender.tenderdatabase.entity.Awarded;
import com.tender.tenderdatabase.entity.Purchaser;
import com.tender.tenderdatabase.entity.Tender;
import com.tender.tenderdatabase.entity.Type;

public interface ICatalogMappers {
    IMapEntities<TenderDto, Tender> forTender();
    IMapEntities<PurchaserDto, Purchaser> forPurchaser();
    IMapEntities<TypeDto, Type> forType();
    IMapEntities<AwardDto, Awarded> forAwarded();
}
