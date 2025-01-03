package com.tender.tenderwebapi.services;

import com.tender.tenderdatabase.entity.Tender;
import com.tender.tenderdatabase.repositories.ICatalogData;
import com.tender.tenderdatabase.repositories.TenderRepository;
import com.tender.tenderwebapi.model.TenderObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        Tender tender = this.repository.getTenders().findAllBySourceId(id).get(0);
        return new TenderObj(tender.getId(),tender.getSourceId(),tender.getDate(),tender.getDeadlineDate(),tender.getDeadlineLengthDays(),tender.getTitle(),tender.getCategory(),tender.getSid(),tender.getSourceUrl());
    }

    @Override
    public void addTender(TenderObj tenderObj) {
        Tender tender = new Tender();
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
        this.repository.getTenders().deleteAll(this.repository.getTenders().findAllBySourceId(id));
    }

    @Override
    public void updateTenderById(long id, TenderObj tenderObj) {
        Tender tender = this.repository.getTenders().findAllBySourceId(id).get(0);
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
}
