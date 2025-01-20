package com.tender.tenderwebclient.services;

import com.tender.tenderwebapi.model.*;
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
    @Autowired
    private RestClient restClient;
    private String tenderBaseUrl = "http://localhost:8080/tenders";

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
        if (response.getStatusCode().isError()) {
            throw new RuntimeException();
        }
        return response.getBody();
    }

    public TenderObj getTenderById(long id){
        ResponseEntity<TenderObj> response = restClient.get()
                .uri(tenderBaseUrl + "/" + id).retrieve()
                .toEntity(new ParameterizedTypeReference<TenderObj>() {});
        if (response.getStatusCode().isError()) {
            throw new RuntimeException();
        }
        return response.getBody();
    }

    public void editTender(long id, TenderObj tenderObj){
        restClient.put()
                .uri(tenderBaseUrl + "/update/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(tenderObj)
                .retrieve()
                .toBodilessEntity();
    }

    public void addTender(TenderObj tenderObj){
        restClient.post()
                .uri(tenderBaseUrl + "/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(tenderObj)
                .retrieve()
                .toBodilessEntity();
    }

    public void deleteTender(long id){
        ResponseEntity<Void> response = restClient.delete()
                .uri(tenderBaseUrl + "/delete/" + id)
                .retrieve()
                .toBodilessEntity();
    }

    public List<PurchaserObj> getAllPurchasers(){
        ResponseEntity<List<PurchaserObj>> response = restClient.get()
                .uri(tenderBaseUrl + "/allPurchaser")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});
        if (response.getStatusCode().isError()) {
            throw new RuntimeException();
        }
        return response.getBody();
    }

    public PurchaserObj getPurchaserByTenderId(long id){
        ResponseEntity<PurchaserObj> response = restClient.get()
                .uri(tenderBaseUrl + "/PurchaserTender/" + id).
                retrieve().toEntity(new ParameterizedTypeReference<PurchaserObj>() {});
        if (response.getStatusCode().isError()) {
            throw new RuntimeException();
        }
        return response.getBody();
    }

    public void editPurchaser(long id, PurchaserObj purchaserObj){
        restClient.put()
                .uri(tenderBaseUrl + "/purchaser/update/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(purchaserObj)
                .retrieve()
                .toBodilessEntity();
    }

    public List<AwardedObj> getAwardedByTenderId(long id){
        ResponseEntity<List<AwardedObj>> response = restClient.get()
                .uri(tenderBaseUrl + "/AwardedTender/" + id).
                retrieve().toEntity(new ParameterizedTypeReference<List<AwardedObj>>() {});
        if (response.getStatusCode().isError()) {
            throw new RuntimeException();
        }
        return response.getBody();
    }

    public List<AwardedObj> getAwardedBySupplierId(long id){
        ResponseEntity<List<AwardedObj>> response = restClient.get()
                .uri(tenderBaseUrl + "/AwardedSupplier/" + id).
                retrieve().toEntity(new ParameterizedTypeReference<List<AwardedObj>>() {});
        if (response.getStatusCode().isError()) {
            throw new RuntimeException();
        }
        return response.getBody();
    }

    public AwardedObj getAwardedById(long id){
        ResponseEntity<AwardedObj> response = restClient.get()
                .uri(tenderBaseUrl + "/Awarded/" + id).
                retrieve().toEntity(new ParameterizedTypeReference<>() {});
        if (response.getStatusCode().isError()) {
            throw new RuntimeException();
        }
        return response.getBody();
    }

    public void editAwarded(long id, AwardedObj awardedObj){
        restClient.put()
                .uri(tenderBaseUrl + "/awarded/update/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(awardedObj)
                .retrieve()
                .toBodilessEntity();
    }

    public TypeObj getTypeByTenderId(long id){
        ResponseEntity<TypeObj> response = restClient.get()
                .uri(tenderBaseUrl + "/TypeTender/" + id).
                retrieve().toEntity(new ParameterizedTypeReference<TypeObj>() {});
        if (response.getStatusCode().isError()) {
            throw new RuntimeException();
        }
        return response.getBody();
    }

    public void editType(long id, TypeObj typeObj){
        restClient.put()
                .uri(tenderBaseUrl + "/type/update/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(typeObj)
                .retrieve()
                .toBodilessEntity();
    }

    public List<SupplierObj> getAllSuppliers(){
        ResponseEntity<List<SupplierObj>> response = restClient.get()
                .uri(tenderBaseUrl + "/allSuppliers").
                retrieve().toEntity(new ParameterizedTypeReference<>() {});
        if (response.getStatusCode().isError()) {
            throw new RuntimeException();
        }
        return response.getBody();
    }

    public void addSupplier(SupplierObj supplierObj){
        restClient.post()
                .uri(tenderBaseUrl + "/supplier/add")
                .contentType(MediaType.APPLICATION_JSON)
                .body(supplierObj)
                .retrieve()
                .toBodilessEntity();
    }

    public void editSupplier(long id, SupplierObj supplierObj){
        restClient.put()
                .uri(tenderBaseUrl + "/supplier/update/" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .body(supplierObj)
                .retrieve()
                .toBodilessEntity();
    }

    public void deleteSupplier(long id){
        ResponseEntity<Void> response = restClient.delete()
                .uri(tenderBaseUrl + "/supplier/delete/" + id)
                .retrieve()
                .toBodilessEntity();
    }

    public List<Long> getAllSuplliersID(){
        ResponseEntity<List<Long>> response = restClient.get()
                .uri(tenderBaseUrl + "/SupplierID")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});
        return response.getBody();
    }


    public List<Integer> getAllTenderID() {
        ResponseEntity<List<Integer>> response = restClient.get()
                .uri(tenderBaseUrl + "/TenderID")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});
        return response.getBody();
    }

    public List<Integer> getAllPurchaserID() {
        ResponseEntity<List<Integer>> response = restClient.get()
                .uri(tenderBaseUrl + "/PurchaserID")
                .retrieve()
                .toEntity(new ParameterizedTypeReference<>() {});
        return response.getBody();
    }
}
