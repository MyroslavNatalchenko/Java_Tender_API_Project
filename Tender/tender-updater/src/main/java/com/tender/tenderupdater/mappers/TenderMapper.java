package com.tender.tenderupdater.mappers;

import com.tender.tenderclient.client.data.TenderDto;
import com.tender.tenderdatabase.entity.Tender;
import org.springframework.stereotype.Component;

@Component
public class TenderMapper implements IMapEntities<TenderDto, Tender> {

    @Override
    public Tender map(TenderDto tenderDto) {
        return map(tenderDto, new Tender());
    }

    @Override
    public Tender map(TenderDto tenderDto, Tender tender) {
        tender.setSourceId((int)tenderDto.id());
        tender.setDate(tenderDto.date());
        tender.setDeadlineDate(tenderDto.deadlineDate());
        tender.setDeadlineLengthDays(tenderDto.deadlineLengthDays());
        tender.setTitle(tenderDto.title());
        tender.setCategory(tenderDto.category());
        tender.setSid(tenderDto.sid());
        tender.setSourceUrl(tenderDto.sourceUrl());
        return tender;
    }
}
