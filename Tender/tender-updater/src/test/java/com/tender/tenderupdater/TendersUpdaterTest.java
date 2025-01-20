package com.tender.tenderupdater;

import com.tender.tenderclient.client.ITendersClient;
import com.tender.tenderclient.client.data.*;
import com.tender.tenderdatabase.entity.*;
import com.tender.tenderdatabase.repositories.*;
import com.tender.tenderupdater.mappers.ICatalogMappers;
import com.tender.tenderupdater.mappers.IMapEntities;
import com.tender.tenderupdater.updater.TendersUpdater;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

class TendersUpdaterTest {

    @Mock
    private ICatalogData catalogData;
    @Mock
    private ITendersClient tendersClient;
    @Mock
    private ICatalogMappers catalogMappers;

    @Mock
    private IMapEntities<TenderDto, Tender> tenderMapper;
    @Mock
    private IMapEntities<PurchaserDto, Purchaser> purchaserMapper;
    @Mock
    private IMapEntities<TypeDto, Type> typeMapper;
    @Mock
    private IMapEntities<AwardDto, Awarded> awardMapper;
    @Mock
    private IMapEntities<SupplierDto, Supplier> supplierMapper;

    @Mock
    private TenderRepository tenderRepository;
    @Mock
    private PurchaserRepository purchaserRepository;
    @Mock
    private TypeRepository typeRepository;
    @Mock
    private AwardedRepository awardedRepository;
    @Mock
    private SupplierRepository supplierRepository;

    @InjectMocks
    private TendersUpdater tendersUpdater;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock repository methods in ICatalogData
        when(catalogData.getTenders()).thenReturn(tenderRepository);
        when(catalogData.getPurchers()).thenReturn(purchaserRepository);
        when(catalogData.getTypes()).thenReturn(typeRepository);
        when(catalogData.getAwarded()).thenReturn(awardedRepository);
        when(catalogData.getSupplier()).thenReturn(supplierRepository);

        // Mock catalogMappers to return specific mappers
        when(catalogMappers.forTender()).thenReturn(tenderMapper);
        when(catalogMappers.forPurchaser()).thenReturn(purchaserMapper);
        when(catalogMappers.forType()).thenReturn(typeMapper);
        when(catalogMappers.forAwarded()).thenReturn(awardMapper);
        when(catalogMappers.forSupplier()).thenReturn(supplierMapper);
    }

    @Test
    void testUpdateByPage() {
        // Arrange
        int page = 1;

        // Mock data for tests
        TenderDto tenderDto = new TenderDto(
                1L,
                "2025-01-01",
                "2025-01-10",
                "10",
                "Tender Title",
                "Category",
                "SID001",
                "https://example.com/tender/1",
                new PurchaserDto(1L, 100L, "SID_PURCHASER", "Purchaser Name"),
                new TypeDto(1L, "TYPE001", "Type Name", "type-slug"),
                List.of(new AwardDto(1L, "2025-01-05", 100.0, 200.0, 300.0, 200L, "1", 2, "500", List.of(new SupplierDto("Supplier", 300L, "supplier-slug"))))
        );

        PagedTenderDto pagedTenderDto = new PagedTenderDto(
                List.of(tenderDto), // Non-empty list of TenderDto
                1,
                1,
                1
        );

        Tender tender = new Tender();
        Purchaser purchaser = new Purchaser();
        Type type = new Type();
        Awarded awarded = new Awarded();
        Supplier supplier = new Supplier();

        // Mocking repository behavior
        when(tenderRepository.findAllSourceIds()).thenReturn(Collections.emptyList());
        when(tendersClient.getTenders(page)).thenReturn(pagedTenderDto);
        when(tendersClient.getTender(1L)).thenReturn(tenderDto);

        // Mocking mapper behavior
        when(tenderMapper.map(tenderDto)).thenReturn(tender);
        when(purchaserMapper.map(any())).thenReturn(purchaser);
        when(typeMapper.map(any())).thenReturn(type);
        when(awardMapper.map(any())).thenReturn(awarded);
        when(supplierMapper.map(any())).thenReturn(supplier);

        // Act
        tendersUpdater.UpdateByPage(page);

        // Assert
        verify(tendersClient, times(1)).getTenders(page);
        verify(tendersClient, times(1)).getTender(1L);
        verify(tenderMapper, times(1)).map(tenderDto);
        verify(purchaserMapper, times(1)).map(any(PurchaserDto.class));
        verify(typeMapper, times(1)).map(any(TypeDto.class));
        verify(awardMapper, times(1)).map(any(AwardDto.class));
        verify(supplierMapper, times(1)).map(any(SupplierDto.class));

        verify(tenderRepository, times(1)).saveAll(anyList());
        verify(purchaserRepository, times(1)).saveAll(anyList());
        verify(typeRepository, times(1)).saveAll(anyList());
        verify(awardedRepository, times(1)).saveAll(anyList());
        verify(supplierRepository, times(1)).saveAll(anyList());
    }
}
