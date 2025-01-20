package com.tender.tenderupdater;

import com.tender.tenderclient.client.data.SupplierDto;
import com.tender.tenderdatabase.entity.Supplier;
import com.tender.tenderupdater.mappers.SupplierMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class SupplierMapperTest {

    private SupplierMapper supplierMapper;

    @BeforeEach
    void setUp() {
        supplierMapper = new SupplierMapper();
    }

    @Test
    void testMapWithNewSupplierObject() {
        // Arrange
        SupplierDto supplierDto = new SupplierDto(
                "Supplier Name",
                1L,
                "supplier-slug"
        );

        // Act
        Supplier supplier = supplierMapper.map(supplierDto);

        // Assert
        assertNotNull(supplier);
        assertEquals(1L, supplier.getSource_id());
        assertEquals("Supplier Name", supplier.getName());
        assertEquals("supplier-slug", supplier.getSlug());
    }

    @Test
    void testMapWithExistingSupplierObject() {
        // Arrange
        SupplierDto supplierDto = new SupplierDto(
                "Updated Supplier Name",
                2L,
                "updated-supplier-slug"
        );

        Supplier existingSupplier = new Supplier();
        existingSupplier.setId(100L); // Existing ID to ensure it doesn't change

        // Act
        Supplier updatedSupplier = supplierMapper.map(supplierDto, existingSupplier);

        // Assert
        assertNotNull(updatedSupplier);
        assertEquals(100L, updatedSupplier.getId()); // Ensure ID is preserved
        assertEquals(2L, updatedSupplier.getSource_id());
        assertEquals("Updated Supplier Name", updatedSupplier.getName());
        assertEquals("updated-supplier-slug", updatedSupplier.getSlug());
    }

    @Test
    void testSpyOnMapper() {
        // Arrange
        SupplierMapper spyMapper = spy(new SupplierMapper());
        SupplierDto supplierDto = new SupplierDto(
                "Spy Supplier Name",
                3L,
                "spy-supplier-slug"
        );

        // Act
        Supplier supplier = spyMapper.map(supplierDto);

        // Assert
        verify(spyMapper, times(1)).map(eq(supplierDto), any(Supplier.class)); // Verify map method is called
        assertNotNull(supplier);
        assertEquals(3L, supplier.getSource_id());
    }
}
