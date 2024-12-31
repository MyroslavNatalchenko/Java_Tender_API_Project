package com.tender.tenderdatabase.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class TendersDataCatalog implements ICatalogData{
    private final TenderRepository tenders;
    private final PurchaserRepository purchasers;
    private final TypeRepository types;
    private final AwardedRepository awarded;
    private final SupplierRepository supplier;

    public TendersDataCatalog(TenderRepository tenders, PurchaserRepository purchasers, TypeRepository types, AwardedRepository awarded, SupplierRepository supplier) {
        this.tenders = tenders;
        this.purchasers = purchasers;
        this.types = types;
        this.awarded = awarded;
        this.supplier = supplier;
    }

    public TenderRepository getTenders() {
        return tenders;
    }

    @Override
    public PurchaserRepository getPurchers() {
        return purchasers;
    }

    @Override
    public TypeRepository getTypes() {
        return types;
    }

    @Override
    public AwardedRepository getAwarded() {
        return awarded;
    }

    @Override
    public SupplierRepository getSupplier() {
        return supplier;
    }
}
