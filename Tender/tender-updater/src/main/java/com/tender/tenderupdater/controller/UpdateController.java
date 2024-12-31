package com.tender.tenderupdater.controller;

import com.tender.tenderupdater.updater.IUpdateTenders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("updater")
public class UpdateController {
    final IUpdateTenders updater;

    public UpdateController(IUpdateTenders updater) {
        this.updater = updater;
    }

    @GetMapping("start")
    public ResponseEntity start(@RequestParam int page){
        updater.UpdateByPage(page);

        return ResponseEntity.ok("Update started on: " + LocalDateTime.now());
    }
}
