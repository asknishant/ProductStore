package com.example.productservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/archive")
public class ArchiveController {

    @Autowired
    private ArchiveService archiveService;

    @PostMapping("/{tableName}")
    public ResponseEntity<String> archiveTable(@PathVariable String tableName) {
        try {
            String archiveTableName = tableName + "_archive";
            archiveService.archiveTable(tableName,archiveTableName);
            return ResponseEntity.ok("Table " + tableName + " archived successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error archiving table " + tableName + ": " + e.getMessage());
        }
    }
}
