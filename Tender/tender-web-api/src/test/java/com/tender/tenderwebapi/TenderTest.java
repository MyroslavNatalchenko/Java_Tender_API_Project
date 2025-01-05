package com.tender.tenderwebapi;

import com.tender.tenderdatabase.entity.Tender;
import com.tender.tenderdatabase.repositories.ICatalogData;
import com.tender.tenderdatabase.repositories.TenderRepository;
import com.tender.tenderdatabase.repositories.TendersDataCatalog;
import com.tender.tenderwebapi.exceptions.tenderEXP.TenderWithSuchIdExistException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TenderTest {
    @Spy
    private ICatalogData repository; // = new TendersDataCatalog(null, null, null, null, null);
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

    // **************************
    // ***** CREATION TESTS *****
    // **************************
    @Test
    public void TenderCreationGood(){
        TenderObj tenderObj = new TenderObj(1,100,"2024-07-07","2024-07-24","17", "popka","service","111","aaaa");
        service.addTender(tenderObj);
        repository.getTenders().findAllSourceIds().add(100);

        Tender tender = InisializeTender(tenderObj);

        when(tenderRepository.findAllBySourceId(100))
                .thenReturn(List.of(tender));

        TenderObj res = service.getTenderById(100);
        assertEquals("2024-07-07", res.date());
    }

    @Test
    public void TenderCreationBad(){
        TenderObj tenderObj = new TenderObj(1,100,"2024-07-07","2024-07-24","17", "popka","service","111","aaaa");
        TenderObj tenderObj2 = new TenderObj(1,100,"2024-07-07","2024-07-24","17", "popka","service","111","aaaa");
        service.addTender(tenderObj);
        repository.getTenders().findAllSourceIds().add(100);
        Exception exception = assertThrows(TenderWithSuchIdExistException.class, () -> service.addTender(tenderObj2));

        assertEquals(exception.getMessage(), "Tender with such ID already exist. Can not create");
    }

    // **************************
    // ****** GET ALL TEST  *****
    // **************************
    @Test
    public void testGetAllTenders() {
        // Подготовим несколько тендеров
        TenderObj to1 = new TenderObj(
                10, 101, "2023-01-01", "2023-01-20",
                "19", "Title1", "Category1", "SID1", "URL1"
        );
        TenderObj to2 = new TenderObj(
                11, 102, "2023-02-01", "2023-02-15",
                "14", "Title2", "Category2", "SID2", "URL2"
        );

        Tender t1 = InisializeTender(to1);
        Tender t2 = InisializeTender(to2);

        when(tenderRepository.findAll()).thenReturn(List.of(t1, t2));

        List<TenderObj> all = service.getAllTenders();
        assertEquals(2, all.size());
        assertEquals(101, all.get(0).sourceId());
        assertEquals(102, all.get(1).sourceId());
    }





    private Tender InisializeTender(TenderObj tenderObj){
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
        return tender;
    }
}
