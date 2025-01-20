package com.tender.tenderwebapi.services;

import com.tender.tenderdatabase.entity.*;
import com.tender.tenderdatabase.repositories.ICatalogData;
import com.tender.tenderwebapi.exceptions.awardedEXP.NoAwardedWithSuchID;
import com.tender.tenderwebapi.exceptions.purchaserEXP.PurchaserNotFoundException;
import com.tender.tenderwebapi.exceptions.supplier.NoSupplerWithSuchIdException;
import com.tender.tenderwebapi.exceptions.supplier.SupplerWithSuchIdExistException;
import com.tender.tenderwebapi.exceptions.tenderEXP.CanNotDeleteTenderException;
import com.tender.tenderwebapi.exceptions.tenderEXP.IncorrectProvidedTenderDataException;
import com.tender.tenderwebapi.exceptions.tenderEXP.TenderNotFoundException;
import com.tender.tenderwebapi.exceptions.tenderEXP.TenderWithSuchIdExistException;
import com.tender.tenderwebapi.model.*;
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

    /**
    /// *****************************************
    /// *****************************************
    /// TENDER TENDER TENDER TENDER TENDER TENDER
    /// *****************************************
    /// *****************************************
     */

    @Override
    public List<TenderObj> getAllTenders() {
        List<Tender> tenders = this.repository.getTenders().findAll();
        if (tenders.isEmpty()) throw new TenderNotFoundException();
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
        LocalDate deadline = LocalDate.parse(tenderObj.deadlineDate());
        LocalDate startdate = LocalDate.parse(tender.getDate());
        tender.setTitle(tenderObj.title());
        tender.setCategory(tenderObj.category());
        tender.setSid(tenderObj.sid());
        tender.setSourceUrl(tenderObj.sourceUrl());
        if (deadline.isAfter(startdate)){
            tender.setDeadlineLengthDays(ChronoUnit.DAYS.between(startdate,deadline) + "");
        } else throw new IncorrectProvidedTenderDataException();

        this.repository.getTenders().save(tender);
    }

    @Override
    public void deleteTenderById(long id) {
        List<Tender> tenders = this.repository.getTenders().findAllBySourceId(id);
        if (tenders.isEmpty()) throw new CanNotDeleteTenderException();
        List<Type> types = this.repository.getTypes().findAllByTender_src_id(id);
        List<Purchaser> purchasers = this.repository.getPurchers().findAllByTender_src_id(id);
        List<Awarded> awards = this.repository.getAwarded().findAllByTender_src_id(id);
        if (!types.isEmpty()) this.repository.getTypes().deleteByTenderSrcId(id);
        if (!awards.isEmpty()) this.repository.getAwarded().deleteByTenderSrcId(id);
        if (!purchasers.isEmpty()) this.repository.getPurchers().deleteByTenderSrcId(id);
        this.repository.getTenders().deleteBySourceId((int) id);
    }

    @Override
    public void updateTenderById(long id, TenderObj tenderObj) {
        List<Tender> tenders = this.repository.getTenders().findAllBySourceId(id);
        if (tenders.isEmpty()) throw new TenderNotFoundException();
        Tender tender=tenders.get(0);

        tender.setSid(tenderObj.sid());
        tender.setCategory(tenderObj.category());
        tender.setDate(tenderObj.date());
        tender.setSourceUrl(tenderObj.sourceUrl());
        tender.setTitle(tenderObj.title());
        tender.setDeadlineDate(tenderObj.deadlineDate());

        if (!tenderObj.deadlineDate().isEmpty()){
            LocalDate deadline = LocalDate.parse(tenderObj.deadlineDate());
            LocalDate startdate = LocalDate.parse(tender.getDate());
            if (deadline.isAfter(startdate)){
                tender.setDeadlineDate(tenderObj.deadlineDate());
                tender.setDeadlineLengthDays(ChronoUnit.DAYS.between(startdate,deadline) + "");
            }
            else throw new IncorrectProvidedTenderDataException();
        }

        this.repository.getTenders().save(tender);
    }

    /**
    /// *******************************************
    /// *******************************************
    /// PURCHASERS PURCHASERS PURCHASERS PURCHASERS
    /// *******************************************
    /// *******************************************
     */

    @Override
    public List<PurchaserObj> getAllPurchasers() {
        List<Purchaser> purchasers = this.repository.getPurchers().findAll();
        if (purchasers.isEmpty()) throw new PurchaserNotFoundException();
        List<PurchaserObj> res = new ArrayList<>();
        for (Purchaser purchaser: purchasers){
            res.add(new PurchaserObj(purchaser.getId(),purchaser.getTender_src_id(),purchaser.getSourceId(),purchaser.getSid(),purchaser.getName()));
        }
        return res;
    }

    @Override
    public PurchaserObj getPurchaserByTenderId(long id) {
        List<Purchaser> purchasers = this.repository.getPurchers().findAllByTender_src_id(id);
        HashSet<Integer> sourceId = new HashSet<>(this.repository.getTenders().findAllSourceIds());
        if (purchasers.isEmpty()) {
            if (sourceId.contains((int)id)){
                Purchaser purchaser = new Purchaser();
                purchaser.setTender(this.repository.getTenders().findAllBySourceId(id).get(0));
                purchaser.setTender_src_id(id);
                this.repository.getPurchers().save(purchaser);
            } else throw new TenderNotFoundException();
        }
        Purchaser purchaser = this.repository.getPurchers().findAllByTender_src_id(id).get(0);
        return new PurchaserObj(purchaser.getId(),purchaser.getTender_src_id(),purchaser.getSourceId(),purchaser.getSid(),purchaser.getName());
    }

    @Override
    public void updatePurchaserById(long id, PurchaserObj purchaserObj) {
        List<Purchaser> purchasers = this.repository.getPurchers().findAllByTender_src_id(id);
        if (purchasers.isEmpty()) throw new PurchaserNotFoundException();
        Purchaser purchaser = purchasers.get(0);
        purchaser.setSourceId(purchaserObj.sourceId());
        purchaser.setName(purchaserObj.name());
        purchaser.setSid(purchaserObj.sid());
        this.repository.getPurchers().save(purchaser);
    }

    /**
    /// ***************************************
    /// ***************************************
    /// AWARDED AWARDED AWARDED AWARDED AWARDED
    /// ***************************************
    /// ***************************************
    */
    @Override
    public List<AwardedObj> getAwardedByTenderId(long id) {
        List<Awarded> awardeds = this.repository.getAwarded().findAllByTender_src_id(id);
        HashSet<Integer> sourceId = new HashSet<>(this.repository.getTenders().findAllSourceIds());
        if (awardeds.isEmpty()) {
            if (sourceId.contains((int)id)){
                Awarded awarded = new Awarded();
                awarded.setTender(this.repository.getTenders().findAllBySourceId(id).get(0));
                awarded.setTender_src_id(id);
                this.repository.getAwarded().save(awarded);
                awardeds.add(awarded);
            } else throw new TenderNotFoundException();
        }
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
    public List<AwardedObj> getAwardedBySupplier(long id) {
        List<Awarded> awarded = this.repository.getAwarded().findAllBySupplier(id);
        List<AwardedObj> res = new ArrayList<>();
        if (awarded.isEmpty()) return res;
        for (Awarded award: awarded){
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
    public void updateAwardedById(long id, AwardedObj awardedObj) {
        HashSet<Long> ids = new HashSet<>(this.repository.getAwarded().findAllSourceIds());
        if (!ids.contains(id)) throw new NoAwardedWithSuchID();
        this.repository.getAwarded().updateAwardedById(id, awardedObj.offersCount(), awardedObj.valueForOne(), awardedObj.valueForTwo(), awardedObj.valueForThree(), awardedObj.suppliersId(), awardedObj.count(), awardedObj.date(), awardedObj.value());
        Awarded awarded = this.repository.getAwarded().findAwardedByBDid(id).get(0);
        awarded.setSupplier(this.repository.getSupplier().findBySource_id(awarded.getSuppliersId()).get(0));
        this.repository.getAwarded().save(awarded);
    }

    public AwardedObj getAwardedbyBdId(long id){
        Awarded award = this.repository.getAwarded().findAwardedByBDid(id).get(0);
        return new AwardedObj(award.getId()
                ,award.getTender_src_id()
                ,award.getDate()
                ,award.getValueForOne()
                ,award.getValueForTwo()
                ,award.getValueForThree()
                ,award.getSuppliersId()
                ,award.getCount()
                ,award.getOffersCount()
                ,award.getValue());
    }

    /**
    /// ***************************************
    /// ***************************************
    /// TYPE TYPE TYPE TYPE TYPE TYPE TYPE TYPE
    /// ***************************************
    /// ***************************************
     */

    @Override
    public TypeObj getTypeByTenderId(long id) {
        List<Type> types = this.repository.getTypes().findAllByTender_src_id(id);
        HashSet<Integer> sourceId = new HashSet<>(this.repository.getTenders().findAllSourceIds());
        if (types.isEmpty()) {
            if (sourceId.contains((int)id)){
                Type type = new Type();
                type.setTender(this.repository.getTenders().findAllBySourceId(id).get(0));
                type.setTender_src_id(id);
                this.repository.getTypes().save(type);
                types.add(type);
            } else throw new TenderNotFoundException();
        }
        Type type = types.get(0);
        return new TypeObj(type.getId(),type.getTender_src_id(),type.getSourceId(),type.getName(),type.getSlug());
    }
    @Override
    public void updateTypeById(long id, TypeObj typeObj) {
        Type type = this.repository.getTypes().findAllByTender_src_id(id).get(0);
        type.setSourceId(typeObj.sourceId());
        type.setName(typeObj.name());
        type.setSlug(typeObj.slug());
        this.repository.getTypes().save(type);
    }

    /**
    /// ********************************************
    /// ********************************************
    /// SUPPLIER SUPPLIER SUPPLIER SUPPLIER SUPPLIER
    /// ********************************************
    /// ********************************************
     */

    @Override
    public List<SupplierObj> getAllSuppliers() {
        List<Supplier> suppliers = this.repository.getSupplier().findAll();
        List<SupplierObj> res = new ArrayList<>();
        for (Supplier supplier: suppliers){
            res.add(new SupplierObj(supplier.getId(),supplier.getSource_id(),supplier.getName(),supplier.getSlug()));
        }
        return res;
    }

    @Override
    public void addSupplier(SupplierObj supplierObj) {
        HashSet<Long> sourceId = new HashSet<>(this.repository.getSupplier().findAllSourceIds());
        if (sourceId.contains(supplierObj.source_id())) throw new SupplerWithSuchIdExistException();

        Supplier supplier = new Supplier();

        supplier.setSource_id(supplierObj.source_id());
        supplier.setSlug(supplierObj.slug());
        supplier.setName(supplierObj.name());

        this.repository.getSupplier().save(supplier);
    }

    @Override
    public void updateSupplier(long id, SupplierObj supplierObj) {
        HashSet<Long> sourceId = new HashSet<>(this.repository.getSupplier().findAllSourceIds());
        if (!sourceId.contains(supplierObj.source_id())) throw new NoSupplerWithSuchIdException();

        Supplier supplier = this.repository.getSupplier().findBySource_id(id).get(0);
        supplier.setName(supplierObj.name());
        supplier.setSlug(supplierObj.slug());
        this.repository.getSupplier().save(supplier);
    }

    @Override
    public void deleteSupplier(long id) {
        int size_awarded = this.repository.getAwarded().findAllBySupplier(id).size();
        if (size_awarded>0) throw new CanNotDeleteTenderException();
        this.repository.getSupplier().delete(this.repository.getSupplier().findBySource_id(id).get(0));
    }


    /**
    /// *************************************
    /// *************************************
    /// *************************************
     */

    public List<Long> getSuppliersID(){
        return this.repository.getSupplier().findAllSourceIds();
    }
    public List<Integer> getTendersID(){
        return this.repository.getTenders().findAllSourceIds();
    }
    public List<Integer> getPurchasersID(){
        return this.repository.getPurchers().findAllSourceIds();
    }
}
