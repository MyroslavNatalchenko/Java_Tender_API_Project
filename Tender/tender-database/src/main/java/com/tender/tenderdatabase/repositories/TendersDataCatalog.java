package com.tender.tenderdatabase.repositories;

import org.springframework.stereotype.Repository;

@Repository
public class TendersDataCatalog implements ICatalogData{
    private final TenderRepository tenders;
    private final PurchaserRepository purchasers;
    private final TypeRepository types;
    private final AwardedRepository awarded;

    public TendersDataCatalog(TenderRepository tenders, PurchaserRepository purchasers, TypeRepository types, AwardedRepository awarded) {
        this.tenders = tenders;
        this.purchasers = purchasers;
        this.types = types;
        this.awarded = awarded;
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
}
