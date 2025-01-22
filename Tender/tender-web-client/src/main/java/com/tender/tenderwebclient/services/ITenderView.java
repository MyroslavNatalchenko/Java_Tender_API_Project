package com.tender.tenderwebclient.services;

import com.tender.tenderwebapi.model.*;

import java.util.List;

public interface ITenderView {
    public List<TenderObj> getAllTenders();
    public TenderObj getTenderById(long id);
    public void editTender(long id, TenderObj tenderObj);
    public void addTender(TenderObj tenderObj);
    public void deleteTender(long id);

    public List<PurchaserObj> getAllPurchasers();
    public PurchaserObj getPurchaserByTenderId(long id);
    public void editPurchaser(long id, PurchaserObj purchaserObj);

    public List<AwardedObj> getAwardedByTenderId(long id);
    public List<AwardedObj> getAwardedBySupplierId(long id);
    public AwardedObj getAwardedById(long id);
    public void editAwarded(long id, AwardedObj awardedObj);

    public TypeObj getTypeByTenderId(long id);
    public void editType(long id, TypeObj typeObj);

    public List<SupplierObj> getAllSuppliers();
    public SupplierObj getSupplierById(long id);
    public void addSupplier(SupplierObj supplierObj);
    public void editSupplier(long id, SupplierObj supplierObj);
    public void deleteSupplier(long id);
}
