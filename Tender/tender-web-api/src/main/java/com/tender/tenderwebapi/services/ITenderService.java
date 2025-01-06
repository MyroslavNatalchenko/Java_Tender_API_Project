package com.tender.tenderwebapi.services;

import com.tender.tenderdatabase.entity.Tender;
import com.tender.tenderdatabase.repositories.TenderRepository;
import com.tender.tenderwebapi.model.PurchaserObj;
import com.tender.tenderwebapi.model.TenderObj;

import java.util.List;

public interface ITenderService {
    public List<TenderObj> getAllTenders();
    public TenderObj getTenderById(long id);
    public void addTender(TenderObj tenderObj);
    public void deleteTenderById(long id);
    public void updateTenderById(long id, TenderObj tenderObj);

    public List<PurchaserObj> getAllPurchasers();
    public PurchaserObj getPurchaserByTenderId(long id);
    public void addPurchaser(PurchaserObj purchaserObj);
    public void deletePurchaserById(long id);
    public void updatePurchaserById(long id, PurchaserObj purchaserObj);
}
