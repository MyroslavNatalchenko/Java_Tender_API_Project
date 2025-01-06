package com.tender.tenderwebapi.services;

import com.tender.tenderdatabase.entity.*;
import com.tender.tenderdatabase.repositories.ICatalogData;
import com.tender.tenderwebapi.exceptions.purchaserEXP.CanNotDeletePurchaserException;
import com.tender.tenderwebapi.exceptions.purchaserEXP.PurchaserNotFoundException;
import com.tender.tenderwebapi.exceptions.tenderEXP.CanNotDeleteTenderException;
import com.tender.tenderwebapi.exceptions.tenderEXP.CanNotEditTenderException;
import com.tender.tenderwebapi.exceptions.tenderEXP.TenderNotFoundException;
import com.tender.tenderwebapi.exceptions.tenderEXP.TenderWithSuchIdExistException;
import com.tender.tenderwebapi.model.AwardedObj;
import com.tender.tenderwebapi.model.PurchaserObj;
import com.tender.tenderwebapi.model.TenderObj;
import com.tender.tenderwebapi.model.TypeObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        this.repository.getTypes().deleteByTenderSrcId(id);
        this.repository.getAwarded().deleteByTenderSrcId(id);
        this.repository.getPurchers().deleteByTenderSrcId(id);
        this.repository.getTenders().deleteBySourceId((int) id);
    }

    @Override
    public void updateTenderById(long id, TenderObj tenderObj) {
        List<Tender> tenders = this.repository.getTenders().findAllBySourceId(id);
        if (tenders.isEmpty()) throw new CanNotEditTenderException();
        Tender tender=tenders.get(0);

        if (tenderObj.sid()!=null) tender.setSid(tenderObj.sid());
        if (tenderObj.category()!=null) tender.setCategory(tenderObj.category());
        if (tenderObj.date()!=null) tender.setDate(tenderObj.date());
        if (tenderObj.sourceUrl()!=null) tender.setSourceUrl(tenderObj.sourceUrl());

        if (tenderObj.deadlineDate()!=null){
            LocalDate deadline = LocalDate.parse(tenderObj.deadlineDate());
            LocalDate startdate = LocalDate.parse(tender.getDate());
            if (deadline.isAfter(startdate)){
                tender.setDeadlineDate(tenderObj.deadlineDate());
                tender.setDeadlineLengthDays(ChronoUnit.DAYS.between(startdate,deadline) + "");
            }
            else throw new CanNotEditTenderException();
        }

        this.repository.getTenders().save(tender);
    }

    /// *************************************
    /// *************************************
    /// *************************************

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
    public PurchaserObj getPurchaserByTenderId(long id) {
        List<Purchaser> purchasers = this.repository.getPurchers().findAllByTender_src_id(id);
        if (purchasers.isEmpty()) throw new PurchaserNotFoundException();
        Purchaser purchaser = purchasers.get(0);
        return new PurchaserObj(purchaser.getId(),purchaser.getTender_src_id(),purchaser.getSourceId(),purchaser.getSid(),purchaser.getName());
    }

    @Override
    public void addPurchaser(PurchaserObj purchaserObj) {
        Purchaser purchaser = new Purchaser();
        purchaser.setSourceId(purchaserObj.sourceId());
        purchaser.setName(purchaserObj.name());
        purchaser.setSid(purchaserObj.sid());
        purchaser.setTender_src_id(purchaserObj.tender_src_id());
        Tender tender = this.repository.getTenders().findAllBySourceId(purchaserObj.tender_src_id()).get(0);
        purchaser.setTender(tender);
        this.repository.getPurchers().save(purchaser);
    }

    @Override
    public void deletePurchaserById(long id) {
        List<Purchaser> purchasers = this.repository.getPurchers().findAllByTender_src_id(id);
        if (purchasers.isEmpty()) throw new CanNotDeletePurchaserException();
        this.repository.getPurchers().deleteByTenderSrcId(id);
    }

    @Override
    public void updatePurchaserById(long id, PurchaserObj purchaserObj) {

    }

    @Override
    public List<AwardedObj> getAwardedByTenderId(long id) {
        List<Awarded> awardeds = this.repository.getAwarded().findAllByTender_src_id(id);
        List<AwardedObj> res = new ArrayList<>();
        for (Awarded award: awardeds){
            res.add(new AwardedObj(award.getId()
                    ,award.getTender_src_id()
                    ,award.getDate()
                    ,award.getValueForOne()
                    ,award.getValueForTwo()
                    ,award.getValueForThree()
                    ,award.getSuppliersId()
                    ,award.getCount()
                    ,award.getOffersCount()
                    ,award.getValue()));
        }
        return res;
    }

    @Override
    public TypeObj getTypeByTenderId(long id) {
        Type type = this.repository.getTypes().findAllByTender_src_id(id).get(0);
        return new TypeObj(type.getId(),type.getTender_src_id(),type.getSourceId(),type.getName(),type.getSlug());
    }

    /// *************************************
    /// *************************************
    /// *************************************


}
