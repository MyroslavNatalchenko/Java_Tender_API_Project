package com.tender.tenderupdater.mappers;

import com.tender.tenderclient.client.data.SupplierDto;
import com.tender.tenderdatabase.entity.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper implements IMapEntities<SupplierDto, Supplier> {
    @Override
    public Supplier map(SupplierDto supplierDto) {
        return map(supplierDto, new Supplier());
    }

    @Override
    public Supplier map(SupplierDto supplierDto, Supplier supplier) {
        supplier.setSource_id(supplierDto.id());
        supplier.setName(supplierDto.name());
        supplier.setSlug(supplierDto.slug());
        return supplier;
    }
}
