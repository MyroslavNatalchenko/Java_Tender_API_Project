package com.tender.tenderwebapi.services;

import com.tender.tenderdatabase.entity.Purchaser;
import com.tender.tenderdatabase.entity.Tender;
import com.tender.tenderdatabase.repositories.ICatalogData;
import com.tender.tenderwebapi.exceptions.tenderEXP.CanNotDeleteTenderException;
import com.tender.tenderwebapi.exceptions.tenderEXP.CanNotEditTenderException;
import com.tender.tenderwebapi.exceptions.tenderEXP.TenderNotFoundException;
import com.tender.tenderwebapi.exceptions.tenderEXP.TenderWithSuchIdExistException;
import com.tender.tenderwebapi.model.PurchaserObj;
import com.tender.tenderwebapi.model.TenderObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class TenderService implements ITenderService{
    private final ICatalogData repository;

    @Autowired
    public TenderService(ICatalogData repository) {
        this.repository = repository;
    }

    @Override
    public List<TenderObj> getAllTenders() {
        List<Tender> tenders = this.repository.getTenders().findAll();
        List<TenderObj> res = new ArrayList<>();
        for (Tender tender: tenders){
            res.add(new TenderObj(tender.getId(),tender.getSourceId(),tender.getDate(),tender.getDeadlineDate(),tender.getDeadlineLengthDays(),tender.getTitle(),tender.getCategory(),tender.getSid(),tender.getSourceUrl()));
        }
        return res;
    }

    @Override
    public TenderObj getTenderById(long id) {
        List<Tender> tenders = this.repository.getTenders().findAllBySourceId(id);
        if (tenders.isEmpty()) throw new TenderNotFoundException();
        Tender tender=tenders.get(0);
        return new TenderObj(tender.getId(),tender.getSourceId(),tender.getDate(),tender.getDeadlineDate(),tender.getDeadlineLengthDays(),tender.getTitle(),tender.getCategory(),tender.getSid(),tender.getSourceUrl());
    }

    @Override
    public void addTender(TenderObj tenderObj) {
        Tender tender = new Tender();
        HashSet<Integer> sourceId = new HashSet<>(this.repository.getTenders().findAllSourceIds());
        System.out.println(sourceId);
        if (sourceId.contains(tenderObj.sourceId())) throw new TenderWithSuchIdExistException();

        tender.setSourceId(tenderObj.sourceId());
        tender.setDate(tenderObj.date());
        tender.setDeadlineDate(tenderObj.deadlineDate());
        tender.setDeadlineLengthDays(tenderObj.deadlineLengthDays());
        tender.setTitle(tenderObj.title());
        tender.setCategory(tenderObj.category());
        tender.setSid(tenderObj.sid());
        tender.setSourceUrl(tenderObj.sourceUrl());
        this.repository.getTenders().save(tender);
    }

    @Override
    public void deleteTenderById(long id) {
        List<Tender> tenders = this.repository.getTenders().findAllBySourceId(id);
        if (tenders.isEmpty()) throw new CanNotDeleteTenderException();
        Tender tender=tenders.get(0);
        this.repository.getTenders().delete(tender);
    }

    @Override
    public void updateTenderById(long id, TenderObj tenderObj) {
        List<Tender> tenders = this.repository.getTenders().findAllBySourceId(id);
        if (tenders.isEmpty()) throw new CanNotEditTenderException();
        Tender tender=tenders.get(0);
        if (tenderObj.sid()!=null){
            tender.setSid(tenderObj.sid());
        }
        if (tenderObj.category()!=null){
            tender.setCategory(tenderObj.category());
        }
        if (tenderObj.date()!=null){
            tender.setDate(tenderObj.date());
        }
        if (tenderObj.deadlineDate()!=null){
            tender.setDeadlineDate(tenderObj.deadlineDate());
        }
        if (tenderObj.deadlineLengthDays()!=null){
            tender.setDeadlineLengthDays(tenderObj.deadlineLengthDays());
        }
        if (tenderObj.sourceUrl()!=null){
            tender.setSourceUrl(tenderObj.sourceUrl());
        }
        this.repository.getTenders().save(tender);
    }

    @Override
    public List<PurchaserObj> getAllPurchasers() {
        List<Purchaser> purchasers = this.repository.getPurchers().findAll();
        List<PurchaserObj> res = new ArrayList<>();
        for (Purchaser purchaser: purchasers){
            res.add(new PurchaserObj(purchaser.getId(),purchaser.getTender_src_id(),purchaser.getSourceId(),purchaser.getSid(),purchaser.getName()));
        }
        return res;
    }

    @Override
    public PurchaserObj getPurchaserById(long id) {
        return null;
    }

    @Override
    public PurchaserObj getPurchaserByTenderId(long id) {
        Purchaser purchaser = this.repository.getPurchers().findAllByTender_src_id(id).get(0);
        return new PurchaserObj(purchaser.getId(),purchaser.getTender_src_id(),purchaser.getSourceId(),purchaser.getSid(),purchaser.getName());
    }

    @Override
    public void addPurchaser(PurchaserObj purchaserObj) {

    }

    @Override
    public void deletePurchaserById(long id) {

    }

    @Override
    public void updatePurchaserById(long id, PurchaserObj purchaserObj) {

    }
}
