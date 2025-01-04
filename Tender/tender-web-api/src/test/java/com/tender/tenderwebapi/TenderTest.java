package com.tender.tenderwebapi;

import com.tender.tenderdatabase.entity.Tender;
import com.tender.tenderdatabase.repositories.ICatalogData;
import com.tender.tenderdatabase.repositories.TenderRepository;
import com.tender.tenderdatabase.repositories.TendersDataCatalog;
import com.tender.tenderwebapi.model.TenderObj;
import com.tender.tenderwebapi.services.TenderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class TenderTest {
    @Spy
    private ICatalogData repository = new TendersDataCatalog(null, null, null, null, null);
    @Mock
    private TenderRepository tenderRepository;
    private TenderService service;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        doReturn(tenderRepository).when(repository).getTenders();

        when(tenderRepository.findAllSourceIds()).thenReturn(new ArrayList<>());

        this.service = new TenderService(repository);
    }

    @Test
    public void TenderCreationGood(){
        TenderObj tenderObj = new TenderObj(1,100,"2024-07-07","2024-07-24","17", "popka","service","111","aaaa");
        service.addTender(tenderObj);

        Tender tender = new Tender();
        tender.setId(tenderObj.id());
        tender.setSourceId(tenderObj.sourceId());
        tender.setDate(tenderObj.date());
        tender.setDeadlineDate(tenderObj.deadlineDate());
        tender.setDeadlineLengthDays(tenderObj.deadlineLengthDays());
        tender.setTitle(tenderObj.title());
        tender.setCategory(tenderObj.category());
        tender.setSid(tenderObj.sid());
        tender.setSourceUrl(tenderObj.sourceUrl());

        when(tenderRepository.findAllBySourceId(100))
                .thenReturn(List.of(tender));

        TenderObj res = service.getTenderById(100);
        assertEquals("2024-07-07", res.date());
    }
}
