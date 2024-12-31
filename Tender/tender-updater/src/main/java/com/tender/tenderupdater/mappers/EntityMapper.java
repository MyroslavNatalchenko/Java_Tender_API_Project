package com.tender.tenderupdater.mappers;

import com.tender.tenderclient.client.data.*;
import com.tender.tenderdatabase.entity.*;
import org.springframework.stereotype.Component;

@Component
public class EntityMapper implements ICatalogMappers{
    private final IMapEntities<TenderDto, Tender> forTender;
    private final IMapEntities<PurchaserDto, Purchaser> forPurchaser;
    private final IMapEntities<TypeDto, Type> forType;
    private final IMapEntities<AwardDto, Awarded> forAwarded;
    private final IMapEntities<SupplierDto, Supplier> forSupplier;

    public EntityMapper(IMapEntities<TenderDto, Tender> forTender, IMapEntities<PurchaserDto, Purchaser> forPurchaser, IMapEntities<TypeDto, Type> forType, IMapEntities<AwardDto, Awarded> forAwarded, IMapEntities<SupplierDto, Supplier> forSupplier) {
        this.forTender = forTender;
        this.forPurchaser = forPurchaser;
        this.forType = forType;
        this.forAwarded = forAwarded;
        this.forSupplier = forSupplier;
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

    @Override
    public IMapEntities<SupplierDto, Supplier> forSupplier() {
        return forSupplier;
    }
}
