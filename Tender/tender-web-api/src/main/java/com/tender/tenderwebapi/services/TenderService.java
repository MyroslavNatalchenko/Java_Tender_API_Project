package com.tender.tenderwebapi.services;

import com.tender.tenderdatabase.entity.Tender;
import com.tender.tenderdatabase.repositories.ICatalogData;
import com.tender.tenderdatabase.repositories.TenderRepository;
import com.tender.tenderwebapi.model.TenderObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TenderService implements ITenderService{
    private final ICatalogData repository;

    @Autowired
    public TenderService(ICatalogData repository) {
        this.repository = repository;
    }

    @Override
    public TenderObj getAllTenders() {
        Tender tender = this.repository.getTenders().findAll().get(0);
        return new TenderObj(tender.getId(),tender.getSourceId(),tender.getDate(),tender.getDeadlineDate(),tender.getDeadlineLengthDays(),tender.getTitle(),tender.getCategory(),tender.getSid(),tender.getSourceUrl());
    }
}
