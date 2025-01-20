package com.tender.tenderclient.client;

import com.tender.tenderclient.client.data.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


public class TendersClient implements ITendersClient {
    private final RestTemplate restTemplate;
    private TenderDto tender;
    ITenderUriProvider baseUri;

    public TendersClient(ITenderUriProvider baseUri) {
        this.restTemplate = new RestTemplate();
        this.baseUri = baseUri;
    }

    @Override
    public PagedTenderDto getTenders(int page) {
        var uri = baseUri.builder()
                .pathSegment("tenders")
                .queryParam("page", page)
                .build().toUriString();
        return restTemplate.getForEntity(uri, PagedTenderDto.class).getBody();
    }

    @Override
    public TenderDto getTender(long id) {
        var uri = baseUri.builder()
                .pathSegment("tenders")
                .pathSegment(id + "").build().toUriString();

        return restTemplate.getForEntity(uri, TenderDto.class).getBody();
    }
//
//    @Override
//    public PurchaserDto getPurchaser(long id) {
//        PurchaserDto res = getTender(id).purchaser();
//
//        return new PurchaserDto(id, res.id(),res.sid(), res.name());
//    }
//
//    @Override
//    public TypeDto getType(long id) {
//        TypeDto res = getTender(id).type();
//
//        return new TypeDto(id, res.id(), res.name(), res.slug());
//    }
//
//    @Override
//    public List<AwardDto> getAward(long id) {
//        List<AwardDto> res = getTender(id).awarded();
//        List<AwardDto> res_f = new ArrayList<>();
//
//        for (AwardDto award: res){
//            res_f.add(new AwardDto(id, award.date(), award.valueForOne(), award.valueForTwo(), award.valueForThree(), award.suppliersId(), award.count(), award.offersCount(), award.value(), award.suppliers()));
//        }
//
//        return res_f;
//    }
}