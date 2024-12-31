package com.tender.tenderdatabase.repositories;

public interface ICatalogData {
    TenderRepository getTenders();
    PurchaserRepository getPurchers();
    TypeRepository getTypes();
    AwardedRepository getAwarded();
    SupplierRepository getSupplier();
}
