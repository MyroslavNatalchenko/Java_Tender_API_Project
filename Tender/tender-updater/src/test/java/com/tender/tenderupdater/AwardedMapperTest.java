package com.tender.tenderupdater;

import com.tender.tenderclient.client.data.AwardDto;
import com.tender.tenderdatabase.entity.Awarded;
import com.tender.tenderupdater.mappers.AwardedMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AwardedMapperTest {

    private AwardedMapper awardedMapper;

    @BeforeEach
    void setUp() {
        awardedMapper = new AwardedMapper();
    }

    @Test
    void testMapWithNewAwardedObject() {
        AwardDto awardDto = new AwardDto(
                1L,
                "2025-01-01",
                100.5,
                200.0,
                300.0,
                12345L,
                "5",
                10,
                "1000",
                null
        );

        Awarded awarded = awardedMapper.map(awardDto);

        assertNotNull(awarded);
        assertEquals(1L, awarded.getTender_src_id());
        assertEquals("2025-01-01", awarded.getDate());
        assertEquals(100.5, awarded.getValueForOne());
        assertEquals(200.0, awarded.getValueForTwo());
        assertEquals(300.0, awarded.getValueForThree());
        assertEquals(12345L, awarded.getSuppliersId());
        assertEquals("5", awarded.getCount());
        assertEquals(10, awarded.getOffersCount());
        assertEquals("1000", awarded.getValue());
    }

    @Test
    void testMapWithExistingAwardedObject() {
        AwardDto awardDto = new AwardDto(
                2L,
                "2025-01-02",
                50.0,
                150.0,
                250.0,
                54321L,
                "3",
                5,
                "500",
                null
        );

        Awarded existingAwarded = new Awarded();
        existingAwarded.setId(100L);

        Awarded updatedAwarded = awardedMapper.map(awardDto, existingAwarded);

        assertNotNull(updatedAwarded);
        assertEquals(100L, updatedAwarded.getId());
        assertEquals(2L, updatedAwarded.getTender_src_id());
        assertEquals("2025-01-02", updatedAwarded.getDate());
        assertEquals(50.0, updatedAwarded.getValueForOne());
        assertEquals(150.0, updatedAwarded.getValueForTwo());
        assertEquals(250.0, updatedAwarded.getValueForThree());
        assertEquals(54321L, updatedAwarded.getSuppliersId());
        assertEquals("3", updatedAwarded.getCount());
        assertEquals(5, updatedAwarded.getOffersCount());
        assertEquals("500", updatedAwarded.getValue());
    }

    @Test
    void testSpyOnMapper() {
        AwardedMapper spyMapper = spy(new AwardedMapper());
        AwardDto awardDto = new AwardDto(
                3L,
                "2025-01-03",
                10.0,
                20.0,
                30.0,
                67890L,
                "1",
                2,
                "300",
                null
        );

        Awarded awarded = spyMapper.map(awardDto);

        verify(spyMapper, times(1)).map(eq(awardDto), any(Awarded.class)); // Verify map method is called
        assertNotNull(awarded);
        assertEquals(3L, awarded.getTender_src_id());
    }
}
