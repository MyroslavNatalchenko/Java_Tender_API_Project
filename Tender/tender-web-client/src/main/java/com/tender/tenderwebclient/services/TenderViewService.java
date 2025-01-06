package com.tender.tenderwebclient.services;

import com.tender.tenderwebapi.model.AwardedObj;
import com.tender.tenderwebapi.model.PurchaserObj;
import com.tender.tenderwebapi.model.TenderObj;
import com.tender.tenderwebapi.model.TypeObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class TenderViewService {
    private RestClient restClient;
    private String tenderBaseUrl = "http://localhost:8080/tenders";

    @Autowired
    public TenderViewService(RestClient restClient,
                             @Value("${tender.base-url}") String tenderBaseUrl) {
        this.restClient = restClient;
        this.tenderBaseUrl = tenderBaseUrl;
    }

    public List<TenderObj> getAllTenders(){
        ResponseEntity<List<TenderObj>> response = restClient.get()
                .uri(tenderBaseUrl + "/allTenders")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    public TenderObj getTenderById(long id){
        ResponseEntity<TenderObj> response = restClient.get()
                .uri(tenderBaseUrl + "/" + id).retrieve()
                .toEntity(new ParameterizedTypeReference<TenderObj>() {});
        return response.getBody();
    }

    public void editTender(long id, TenderObj tenderObj){
        System.out.println(id);
        System.out.println(tenderObj);
        restClient.put()
                .uri(tenderBaseUrl + "/update/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tenderObj)
                .retrieve()
                .toBodilessEntity();
    }

    public List<PurchaserObj> getAllPurchasers(){
        ResponseEntity<List<PurchaserObj>> response = restClient.get()
                .uri(tenderBaseUrl + "/allPurchaser")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    public PurchaserObj getPurchaserByTenderId(long id){
        ResponseEntity<PurchaserObj> response = restClient.get()
                .uri(tenderBaseUrl + "/PurchaserTender/" + id).
                retrieve().toEntity(new ParameterizedTypeReference<PurchaserObj>() {});
        return response.getBody();
    }

    public List<AwardedObj> getAwardedByTenderId(long id){
        ResponseEntity<List<AwardedObj>> response = restClient.get()
                .uri(tenderBaseUrl + "/AwardedTender/" + id).
                retrieve().toEntity(new ParameterizedTypeReference<List<AwardedObj>>() {});
        return response.getBody();
    }

    public TypeObj getTypeByTenderId(long id){
        ResponseEntity<TypeObj> response = restClient.get()
                .uri(tenderBaseUrl + "/TypeTender/" + id).
                retrieve().toEntity(new ParameterizedTypeReference<TypeObj>() {});
        return response.getBody();
    }
}
