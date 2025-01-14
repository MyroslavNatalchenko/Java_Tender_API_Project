package com.tender.tenderwebapi.services;

import com.tender.tenderdatabase.entity.Awarded;
import com.tender.tenderdatabase.entity.Supplier;
import com.tender.tenderdatabase.entity.Tender;
import com.tender.tenderdatabase.repositories.TenderRepository;
import com.tender.tenderwebapi.model.*;

import java.util.List;

public interface ITenderService {
    public List<TenderObj> getAllTenders();
    public TenderObj getTenderById(long id);
    public void addTender(TenderObj tenderObj);
    public void deleteTenderById(long id);
    public void updateTenderById(long id, TenderObj tenderObj);

    public List<PurchaserObj> getAllPurchasers();
    public PurchaserObj getPurchaserByTenderId(long id);
    public void updatePurchaserById(long id, PurchaserObj purchaserObj);

    public List<AwardedObj> getAwardedByTenderId(long id);
    public void updateAwardedById(long id, AwardedObj awardedObj);

    public TypeObj getTypeByTenderId(long id);
    public void updateTypeById(long id, TypeObj typeObj);

    public List<SupplierObj> getAllSuppliers();
    public void addSupplier(SupplierObj supplierObj);
    public void updateSupplier(long id, SupplierObj supplierObj);
}
