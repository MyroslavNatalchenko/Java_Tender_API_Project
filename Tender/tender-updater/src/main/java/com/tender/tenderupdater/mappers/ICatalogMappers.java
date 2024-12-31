package com.tender.tenderupdater.mappers;

import com.tender.tenderclient.client.data.*;
import com.tender.tenderdatabase.entity.*;

public interface ICatalogMappers {
    IMapEntities<TenderDto, Tender> forTender();
    IMapEntities<PurchaserDto, Purchaser> forPurchaser();
    IMapEntities<TypeDto, Type> forType();
    IMapEntities<AwardDto, Awarded> forAwarded();
    IMapEntities<SupplierDto, Supplier> forSupplier();
}
