package com.tender.tenderupdater;

import com.tender.tenderclient.client.data.TypeDto;
import com.tender.tenderdatabase.entity.Type;
import com.tender.tenderupdater.mappers.TypeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class TypeMapperTest {

    private TypeMapper typeMapper;

    @BeforeEach
    void setUp() {
        typeMapper = new TypeMapper();
    }

    @Test
    void testMapWithNewTypeObject() {
        // Arrange
        TypeDto typeDto = new TypeDto(
                101L,
                "123",
                "Type Name",
                "type-slug"
        );

        // Act
        Type type = typeMapper.map(typeDto);

        // Assert
        assertNotNull(type);
        assertEquals(101L, type.getTender_src_id());
        assertEquals("123", type.getSourceId());
        assertEquals("Type Name", type.getName());
        assertEquals("type-slug", type.getSlug());
    }

    @Test
    void testMapWithExistingTypeObject() {
        // Arrange
        TypeDto typeDto = new TypeDto(
                102L,
                "456",
                "Updated Type Name",
                "updated-type-slug"
        );

        Type existingType = new Type();
        existingType.setId(200L); // Existing ID to ensure it doesn't change

        // Act
        Type updatedType = typeMapper.map(typeDto, existingType);

        // Assert
        assertNotNull(updatedType);
        assertEquals(200L, updatedType.getId()); // Ensure ID is preserved
        assertEquals(102L, updatedType.getTender_src_id());
        assertEquals("456", updatedType.getSourceId());
        assertEquals("Updated Type Name", updatedType.getName());
        assertEquals("updated-type-slug", updatedType.getSlug());
    }

    @Test
    void testSpyOnMapper() {
        // Arrange
        TypeMapper spyMapper = spy(new TypeMapper());
        TypeDto typeDto = new TypeDto(
                103L,
                "789",
                "Spy Type Name",
                "spy-type-slug"
        );

        // Act
        Type type = spyMapper.map(typeDto);

        // Assert
        verify(spyMapper, times(1)).map(eq(typeDto), any(Type.class)); // Verify map method is called
        assertNotNull(type);
        assertEquals(103L, type.getTender_src_id());
    }
}
