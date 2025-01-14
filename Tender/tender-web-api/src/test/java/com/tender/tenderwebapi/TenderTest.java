package com.tender.tenderwebapi;

import com.tender.tenderdatabase.entity.*;
import com.tender.tenderdatabase.repositories.*;
import com.tender.tenderwebapi.controller.PurchaserController;
import com.tender.tenderwebapi.exceptions.purchaserEXP.PurchaserNotFoundException;
import com.tender.tenderwebapi.exceptions.supplier.SupplerWithSuchIdExistException;
import com.tender.tenderwebapi.exceptions.tenderEXP.CanNotDeleteTenderException;
import com.tender.tenderwebapi.exceptions.tenderEXP.IncorrectProvidedTenderDataException;
import com.tender.tenderwebapi.exceptions.tenderEXP.TenderNotFoundException;
import com.tender.tenderwebapi.exceptions.tenderEXP.TenderWithSuchIdExistException;
import com.tender.tenderwebapi.model.*;
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
    private ICatalogData repository;
    @Mock
    private TenderRepository tenderRepository;
    @Mock
    private TypeRepository typeRepository;
    @Mock
    private AwardedRepository awardedRepository;
    @Mock
    private PurchaserRepository purchaserRepository;
    @Mock
    private SupplierRepository supplierRepository;
    private TenderService service;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        doReturn(tenderRepository).when(repository).getTenders();
        doReturn(supplierRepository).when(repository).getSupplier();
        doReturn(typeRepository).when(repository).getTypes();
        doReturn(awardedRepository).when(repository).getAwarded();
        doReturn(purchaserRepository).when(repository).getPurchers();

        when(tenderRepository.findAllSourceIds()).thenReturn(new ArrayList<>());

        this.service = new TenderService(repository);
    }

    // **************************
    // ***** CREATION TESTS *****
    // **************************
    @Test
    public void testGetAllTendersSuccess() {
        Tender t1 = createTender(1, 101, "2023-01-01", "2023-01-10", "Title1");
        Tender t2 = createTender(2, 102, "2023-02-01", "2023-02-10", "Title2");
        when(tenderRepository.findAll()).thenReturn(List.of(t1, t2));

        List<TenderObj> tenders = service.getAllTenders();
        assertEquals(2, tenders.size());
    }

    @Test
    public void testGetTenderByIdSuccess() {
        Tender t = createTender(1, 101, "2023-01-01", "2023-01-10", "Title");
        when(tenderRepository.findAllBySourceId(101)).thenReturn(List.of(t));

        TenderObj tender = service.getTenderById(101);
        assertEquals(101, tender.sourceId());
    }

    @Test
    public void testGetTenderByIdNotFound() {
        when(tenderRepository.findAllBySourceId(101)).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(TenderNotFoundException.class, () -> service.getTenderById(101));
        assertEquals("No Tender with such ID", exception.getMessage());
    }

    @Test
    public void testAddTenderSuccess() {
        TenderObj tenderObj = new TenderObj(0, 100, "2023-01-01", "2023-01-10", "9", "Title", "Category", "SID", "URL");
        when(tenderRepository.findAllSourceIds()).thenReturn(new ArrayList<>());

        service.addTender(tenderObj);
        verify(tenderRepository, times(1)).save(any(Tender.class));
    }

    @Test
    public void testAddTenderWithDuplicateId() {
        TenderObj tenderObj = new TenderObj(0, 100, "2023-01-01", "2023-01-10", "9", "Title", "Category", "SID", "URL");
        when(tenderRepository.findAllSourceIds()).thenReturn(List.of(100));

        Exception exception = assertThrows(TenderWithSuchIdExistException.class, () -> service.addTender(tenderObj));
        assertEquals("Tender with such ID already exist. Can not create", exception.getMessage());
    }

    @Test
    public void testAddTenderWithIncorrectDates() {
        TenderObj tenderObj = new TenderObj(0, 100, "2023-01-10", "2023-01-01", "9", "Title", "Category", "SID", "URL");

        Exception exception = assertThrows(IncorrectProvidedTenderDataException.class, () -> service.addTender(tenderObj));
        assertEquals("Information provided is incorrect. Check it again before sending (for example: dates)", exception.getMessage());
    }

    @Test
    public void testDeleteTenderByIdSuccess() {
        Tender t = createTender(1, 101, "2023-01-01", "2023-01-10", "Title");
        when(tenderRepository.findAllBySourceId(101)).thenReturn(List.of(t));

        service.deleteTenderById(101);
        verify(tenderRepository, times(1)).deleteBySourceId(101);
    }

    @Test
    public void testDeleteTenderByIdNotFound() {
        when(tenderRepository.findAllBySourceId(101)).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(CanNotDeleteTenderException.class, () -> service.deleteTenderById(101));
        assertEquals("Can not delete Tender. ID is incorrect", exception.getMessage());
    }

    @Test
    public void testUpdateTenderByIdSuccess() {
        Tender existingTender = createTender(1, 101, "2023-01-01", "2023-01-10", "Old Title");
        when(tenderRepository.findAllBySourceId(101)).thenReturn(List.of(existingTender));

        TenderObj updatedTender = new TenderObj(1, 101, "2023-01-01", "2023-01-15", "14", "New Title", "Category", "SID", "URL");
        service.updateTenderById(101, updatedTender);

        verify(tenderRepository, times(1)).save(existingTender);
        assertEquals("New Title", existingTender.getTitle());
    }

    @Test
    public void testUpdateTenderByIdNotFound() {
        when(tenderRepository.findAllBySourceId(101)).thenReturn(new ArrayList<>());

        TenderObj updatedTender = new TenderObj(1, 101, "2023-01-01", "2023-01-15", "14", "New Title", "Category", "SID", "URL");
        Exception exception = assertThrows(TenderNotFoundException.class, () -> service.updateTenderById(101, updatedTender));
        assertEquals("No Tender with such ID", exception.getMessage());
    }

    // **************************
    // *** PURCHASER TESTS ******
    // **************************

    @Test
    public void testGetAllPurchasersSuccess() {
        Purchaser p1 = createPurchaser(1, 101, "Name1");
        Purchaser p2 = createPurchaser(2, 102, "Name2");
        when(purchaserRepository.findAll()).thenReturn(List.of(p1, p2));

        List<PurchaserObj> purchasers = service.getAllPurchasers();
        assertEquals(2, purchasers.size());
        assertEquals("Name1", purchasers.get(0).name());
    }

    @Test
    public void testGetPurchaserByTenderIdSuccess() {
        Purchaser p = createPurchaser(1, 101, "Name");
        when(purchaserRepository.findAllByTender_src_id(101)).thenReturn(List.of(p));

        PurchaserObj purchaser = service.getPurchaserByTenderId(101);
        assertEquals("Name", purchaser.name());
    }

    @Test
    public void testGetPurchaserByTenderIdNotFound() {
        when(purchaserRepository.findAllByTender_src_id(101)).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(TenderNotFoundException.class, () -> service.getPurchaserByTenderId(101));
        assertEquals("No Tender with such ID", exception.getMessage());
    }

    // **************************
    // ***** TYPE TESTS *********
    // **************************

    @Test
    public void testGetTypeByTenderIdSuccess() {
        Type type = createType(1, 101, "TypeName", "TypeSlug");
        when(typeRepository.findAllByTender_src_id(101)).thenReturn(List.of(type));

        TypeObj typeObj = service.getTypeByTenderId(101);
        assertEquals("TypeName", typeObj.name());
    }

    @Test
    public void testGetTypeByTenderIdNotFound() {
        when(typeRepository.findAllByTender_src_id(101)).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(TenderNotFoundException.class, () -> service.getTypeByTenderId(101));
        assertEquals("No Tender with such ID", exception.getMessage());
    }

    // **************************
    // ***** AWARDED TESTS ******
    // **************************

    @Test
    public void testGetAwardedByTenderIdSuccess() {
        Awarded award = createAwarded(1, 101, 1000.0, "AwardedName");
        when(awardedRepository.findAllByTender_src_id(101)).thenReturn(List.of(award));

        List<AwardedObj> awardedList = service.getAwardedByTenderId(101);
        assertEquals(1, awardedList.size());
        assertEquals("1000.0", awardedList.get(0).value());
    }

    @Test
    public void testGetAwardedByTenderIdNotFound() {
        when(awardedRepository.findAllByTender_src_id(101)).thenReturn(new ArrayList<>());

        Exception exception = assertThrows(TenderNotFoundException.class, () -> service.getAwardedByTenderId(101));
        assertEquals("No Tender with such ID", exception.getMessage());
    }

    // **************************
    // **** SUPPLIER TESTS ******
    // **************************

    @Test
    public void testGetAllSuppliersSuccess() {
        Supplier s1 = createSupplier(1, "Supplier1", "Slug1");
        Supplier s2 = createSupplier(2, "Supplier2", "Slug2");
        when(supplierRepository.findAll()).thenReturn(List.of(s1, s2));

        List<SupplierObj> suppliers = service.getAllSuppliers();
        assertEquals(2, suppliers.size());
        assertEquals("Supplier1", suppliers.get(0).name());
    }

    @Test
    public void testAddSupplierSuccess() {
        SupplierObj supplierObj = new SupplierObj(0, 101, "Supplier", "Slug");
        when(supplierRepository.findAllSourceIds()).thenReturn(new ArrayList<>());

        service.addSupplier(supplierObj);
        verify(supplierRepository, times(1)).save(any(Supplier.class));
    }

    @Test
    public void testAddSupplierWithDuplicateId() {
        SupplierObj supplierObj = new SupplierObj(0, 101, "Supplier", "Slug");
        when(supplierRepository.findAllSourceIds()).thenReturn(List.of(101L));

        Exception exception = assertThrows(SupplerWithSuchIdExistException.class, () -> service.addSupplier(supplierObj));
        assertEquals("Supplier with such ID already exist. Can not create", exception.getMessage());
    }

    // **************************
    // *** HELPER METHODS *******
    // **************************

    private Tender createTender(long id, int sourceId, String date, String deadlineDate, String title) {
        Tender tender = new Tender();
        tender.setId(id);
        tender.setSourceId(sourceId);
        tender.setDate(date);
        tender.setDeadlineDate(deadlineDate);
        tender.setTitle(title);
        return tender;
    }

    private Purchaser createPurchaser(long id, int tenderId, String name) {
        Purchaser purchaser = new Purchaser();
        purchaser.setId(id);
        purchaser.setTender_src_id(tenderId);
        purchaser.setName(name);
        return purchaser;
    }

    private Type createType(long id, int tenderId, String name, String slug) {
        Type type = new Type();
        type.setId(id);
        type.setTender_src_id(tenderId);
        type.setName(name);
        type.setSlug(slug);
        return type;
    }

    private Awarded createAwarded(long id, int tenderId, double value, String name) {
        Awarded awarded = new Awarded();
        awarded.setId(id);
        awarded.setTender_src_id(tenderId);
        awarded.setValue(String.valueOf(value));
        return awarded;
    }

    private Supplier createSupplier(long id, String name, String slug) {
        Supplier supplier = new Supplier();
        supplier.setId(id);
        supplier.setName(name);
        supplier.setSlug(slug);
        return supplier;
    }
}
