package com.tender.tenderupdater.mappers;

import com.tender.tenderclient.client.data.AwardDto;
import com.tender.tenderclient.client.data.PurchaserDto;
import com.tender.tenderclient.client.data.TenderDto;
import com.tender.tenderclient.client.data.TypeDto;
import com.tender.tenderdatabase.entity.Awarded;
import com.tender.tenderdatabase.entity.Purchaser;
import com.tender.tenderdatabase.entity.Tender;
import com.tender.tenderdatabase.entity.Type;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper implements ICatalogMappers{
    private final IMapEntities<TenderDto, Tender> forTender;
    private final IMapEntities<PurchaserDto, Purchaser> forPurchaser;
    private final IMapEntities<TypeDto, Type> forType;
    private final IMapEntities<AwardDto, Awarded> forAwarded;

    public EntityMapper(IMapEntities<TenderDto, Tender> forTender, IMapEntities<PurchaserDto, Purchaser> forPurchaser, IMapEntities<TypeDto, Type> forType, IMapEntities<AwardDto, Awarded> forAwarded) {
        this.forTender = forTender;
        this.forPurchaser = forPurchaser;
        this.forType = forType;
        this.forAwarded = forAwarded;
    }

    @Override
    public IMapEntities<TenderDto, Tender> forTender() {
        return forTender;
    }

    @Override
    public IMapEntities<PurchaserDto, Purchaser> forPurchaser() {
        return forPurchaser;
    }

    @Override
    public IMapEntities<TypeDto, Type> forType() {
        return forType;
    }

    @Override
    public IMapEntities<AwardDto, Awarded> forAwarded() {
        return forAwarded;
    }
}
