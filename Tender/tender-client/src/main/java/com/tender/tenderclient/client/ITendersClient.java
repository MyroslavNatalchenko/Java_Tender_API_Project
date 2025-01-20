package com.tender.tenderclient.client;

import com.tender.tenderclient.client.data.*;

import java.util.List;

public interface ITendersClient {
    /**
     * Получить список тендеров.
     * @param page Страница.
     * @return PagedTenderDto.
     */
    PagedTenderDto getTenders(int page);
    TenderDto getTender(long id);
//    PurchaserDto getPurchaser(long id);
//    TypeDto getType(long id);
//    List<AwardDto> getAward(long id);
}