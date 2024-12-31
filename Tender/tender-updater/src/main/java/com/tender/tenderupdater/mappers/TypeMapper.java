package com.tender.tenderupdater.mappers;

import com.tender.tenderclient.client.data.TypeDto;
import com.tender.tenderdatabase.entity.Type;
import org.springframework.stereotype.Component;

@Component
public class TypeMapper implements IMapEntities<TypeDto, Type> {
    @Override
    public Type map(TypeDto typeDto) {
        return map(typeDto, new Type());
    }

    @Override
    public Type map(TypeDto typeDto, Type type) {
        type.setSourceId(typeDto.id());
        type.setName(typeDto.name());
        type.setSlug(typeDto.slug());
        type.setTender_src_id(typeDto.tender_src_id());
        return type;
    }
}
