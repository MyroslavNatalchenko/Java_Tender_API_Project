package com.tender.tenderclient;

import com.tender.tenderclient.client.*;
import com.tender.tenderclient.client.data.*;

public class Main {
    public static void main(String[] args) {
        ITenderUriProvider Espania = new TenderUriProvider("tenders.guru/api", "es");
        TendersClient es_client = new TendersClient(Espania);
        CheckItOut(es_client);
    }

    public static void CheckItOut(ITendersClient es_client){
        PagedTenderDto es_tenders = es_client.getTenders(1);

        System.out.println("Espania Tenders: ");
        for (TenderDto tender : es_tenders.tenders()) {
            TenderDto tenderDto = es_client.getTender(tender.id());
            System.out.println("SourceID: " + tender.id());
            System.out.println("DeathDate: " + tenderDto.deadlineDate());
            System.out.printf("ID: %s, Name: %s, Deadline Date: %s%n",
                    tender.id(), tender.title(), tender.deadlineDate());
            System.out.printf("Purchaser ID: %s%n", tender.purchaser().id());
        }
    }
}
