package com.tender.tenderupdater;

import com.tender.tenderclient.client.data.TenderDto;
import com.tender.tenderdatabase.entity.Tender;
import com.tender.tenderupdater.mappers.TenderMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TenderMapperTest {

    private TenderMapper tenderMapper;

    @BeforeEach
    void setUp() {
        tenderMapper = new TenderMapper();
    }

    @Test
    void testMapWithNewTenderObject() {
        // Arrange
        TenderDto tenderDto = new TenderDto(
                1L,
                "2025-01-01",
                "2025-01-10",
                "9",
                "Tender Title",
                "Category Name",
                "SID001",
                "https://example.com/tender/1",
                null,
                null,
                null
        );

        // Act
        Tender tender = tenderMapper.map(tenderDto);

        // Assert
        assertNotNull(tender);
        assertEquals(1, tender.getSourceId());
        assertEquals("2025-01-01", tender.getDate());
        assertEquals("2025-01-10", tender.getDeadlineDate());
        assertEquals("9", tender.getDeadlineLengthDays());
        assertEquals("Tender Title", tender.getTitle());
        assertEquals("Category Name", tender.getCategory());
        assertEquals("SID001", tender.getSid());
        assertEquals("https://example.com/tender/1", tender.getSourceUrl());
    }

    @Test
    void testMapWithExistingTenderObject() {
        // Arrange
        TenderDto tenderDto = new TenderDto(
                2L,
                "2025-02-01",
                "2025-02-10",
                "10",
                "Updated Tender Title",
                "Updated Category",
                "SID002",
                "https://example.com/tender/2",
                null,
                null,
                null
        );

        Tender existingTender = new Tender();
        existingTender.setId(100L); // Existing ID to ensure it doesn't change

        // Act
        Tender updatedTender = tenderMapper.map(tenderDto, existingTender);

        // Assert
        assertNotNull(updatedTender);
        assertEquals(100L, updatedTender.getId()); // Ensure ID is preserved
        assertEquals(2, updatedTender.getSourceId());
        assertEquals("2025-02-01", updatedTender.getDate());
        assertEquals("2025-02-10", updatedTender.getDeadlineDate());
        assertEquals("10", updatedTender.getDeadlineLengthDays());
        assertEquals("Updated Tender Title", updatedTender.getTitle());
        assertEquals("Updated Category", updatedTender.getCategory());
        assertEquals("SID002", updatedTender.getSid());
        assertEquals("https://example.com/tender/2", updatedTender.getSourceUrl());
    }

    @Test
    void testSpyOnMapper() {
        // Arrange
        TenderMapper spyMapper = spy(new TenderMapper());
        TenderDto tenderDto = new TenderDto(
                3L,
                "2025-03-01",
                "2025-03-10",
                "8",
                "Spy Tender Title",
                "Spy Category",
                "SID003",
                "https://example.com/tender/3",
                null,
                null,
                null
        );

        // Act
        Tender tender = spyMapper.map(tenderDto);

        // Assert
        verify(spyMapper, times(1)).map(eq(tenderDto), any(Tender.class)); // Verify map method is called
        assertNotNull(tender);
        assertEquals(3, tender.getSourceId());
    }
}
