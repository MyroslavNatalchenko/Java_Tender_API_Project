package com.tender.tenderwebapi.services;

import com.tender.tenderdatabase.entity.Tender;
import com.tender.tenderdatabase.repositories.TenderRepository;
import com.tender.tenderwebapi.model.TenderObj;

import java.util.List;

public interface ITenderService {
    public TenderObj getAllTenders();
}
