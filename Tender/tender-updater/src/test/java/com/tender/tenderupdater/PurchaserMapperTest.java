package com.tender.tenderupdater;

import com.tender.tenderclient.client.data.PurchaserDto;
import com.tender.tenderdatabase.entity.Purchaser;
import com.tender.tenderupdater.mappers.PurchaserMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class PurchaserMapperTest {

    private PurchaserMapper purchaserMapper;

    @BeforeEach
    void setUp() {
        purchaserMapper = new PurchaserMapper();
    }

    @Test
    void testMapWithNewPurchaserObject() {
        // Arrange
        PurchaserDto purchaserDto = new PurchaserDto(
                101L,
                1L,
                "SID001",
                "Purchaser Name"
        );

        // Act
        Purchaser purchaser = purchaserMapper.map(purchaserDto);

        // Assert
        assertNotNull(purchaser);
        assertEquals(101L, purchaser.getTender_src_id());
        assertEquals(1, purchaser.getSourceId());
        assertEquals("SID001", purchaser.getSid());
        assertEquals("Purchaser Name", purchaser.getName());
    }

    @Test
    void testMapWithExistingPurchaserObject() {
        // Arrange
        PurchaserDto purchaserDto = new PurchaserDto(
                102L,
                2L,
                "SID002",
                "Updated Purchaser Name"
        );

        Purchaser existingPurchaser = new Purchaser();
        existingPurchaser.setId(50L); // Existing ID to check it doesn't change

        // Act
        Purchaser updatedPurchaser = purchaserMapper.map(purchaserDto, existingPurchaser);

        // Assert
        assertNotNull(updatedPurchaser);
        assertEquals(50L, updatedPurchaser.getId()); // Ensure ID is preserved
        assertEquals(102L, updatedPurchaser.getTender_src_id());
        assertEquals(2, updatedPurchaser.getSourceId());
        assertEquals("SID002", updatedPurchaser.getSid());
        assertEquals("Updated Purchaser Name", updatedPurchaser.getName());
    }

    @Test
    void testSpyOnMapper() {
        // Arrange
        PurchaserMapper spyMapper = spy(new PurchaserMapper());
        PurchaserDto purchaserDto = new PurchaserDto(
                103L,
                3L,
                "SID003",
                "Spy Purchaser Name"
        );

        // Act
        Purchaser purchaser = spyMapper.map(purchaserDto);

        // Assert
        verify(spyMapper, times(1)).map(eq(purchaserDto), any(Purchaser.class)); // Verify map method is called
        assertNotNull(purchaser);
        assertEquals(103L, purchaser.getTender_src_id());
    }
}
