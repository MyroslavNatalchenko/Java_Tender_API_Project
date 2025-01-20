package com.tender.tenderclient;

import com.tender.tenderclient.client.ITenderUriProvider;
import com.tender.tenderclient.client.TendersClient;
import com.tender.tenderclient.client.data.PagedTenderDto;
import com.tender.tenderclient.client.data.TenderDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TendersClientTest {

    @InjectMocks
    private TendersClient tendersClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ITenderUriProvider uriProvider;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getTendersAll() {
        // Настраиваем мок для ITenderUriProvider
        String baseUrl = "tenders.guru/api";
        String country = "es";
        when(uriProvider.baseUrl()).thenReturn(baseUrl);
        when(uriProvider.country()).thenReturn(country);
        when(uriProvider.builder()).thenReturn(
                UriComponentsBuilder.newInstance()
                        .scheme("https")
                        .host(baseUrl)
                        .pathSegment(country)
        );

        // Ожидаемый URI
        String uri = "https://tenders.guru/api/es/tenders?page=1";

        // Подготовка мок-ответа
        PagedTenderDto mockResponse = new PagedTenderDto(
                Collections.emptyList(),
                1,
                7146,
                714598
        );

        // Настраиваем поведение restTemplate
        when(restTemplate.getForEntity(uri, PagedTenderDto.class))
                .thenReturn(new org.springframework.http.ResponseEntity<>(mockResponse, org.springframework.http.HttpStatus.OK));

        // Вызываем метод
        PagedTenderDto result = tendersClient.getTenders(1);

        // Проверяем результат
        assertEquals(1, result.page_number());
        assertEquals(7146, result.page_count());
        assertEquals(714598, result.total());
        assertEquals(100, result.tenders().size()); // Моковый ответ не содержит данных
    }


    @Test
    void getTenderById() {
        // Настраиваем мок для ITenderUriProvider
        String baseUrl = "tenders.guru/api";
        String country = "es";
        when(uriProvider.baseUrl()).thenReturn(baseUrl);
        when(uriProvider.country()).thenReturn(country);
        when(uriProvider.builder()).thenReturn(
                UriComponentsBuilder.newInstance()
                        .scheme("https")
                        .host(baseUrl)
                        .pathSegment(country)
        );

        // Настраиваем URI
        long tenderId = 831333;
        String uri = "https://tenders.guru/api/es/tenders/831333";

        TenderDto mockTender = new TenderDto(
                tenderId,
                "2021-06-29",
                "2021-06-07",
                "22",
                "Servicio de producción de células mesenquimales troncales adultas autólogas y alogénicas de medula ósea expandidas, vinculado al proyecto de investigación PIC18/00001",
                "services",
                "7791427",
                "https://contrataciondelestado.es/wps/poc?uri=deeplink:detalle_licitacion&idEvl=IQ7l9PalhEWiEJrVRqloyA%3D%3D",
                null,
                null,
                Collections.emptyList()
        );

        when(restTemplate.getForEntity(uri, TenderDto.class))
                .thenReturn(new org.springframework.http.ResponseEntity<>(mockTender, org.springframework.http.HttpStatus.OK));

        // Вызываем метод
        TenderDto result = tendersClient.getTender(tenderId);

        // Проверяем результат
        assertEquals(tenderId, result.id());
        assertEquals("Servicio de producción de células mesenquimales troncales adultas autólogas y alogénicas de medula ósea expandidas, vinculado al proyecto de investigación PIC18/00001", result.title());
        assertEquals("services", result.category());
    }
}
