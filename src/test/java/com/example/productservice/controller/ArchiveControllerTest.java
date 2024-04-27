package com.example.productservice.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ArchiveController.class) // Specify the controller to be tested
@AutoConfigureMockMvc
public class ArchiveControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ArchiveService archiveService;

    @Test
    public void testArchiveTable() throws Exception {
        // Define table name
        String tableName = "example_table";
        String archiveTableName = tableName + "_archive";

        // Perform POST request to controller endpoint with table name as path variable
        mockMvc.perform(post("/api/archive/{tableName}", tableName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        // Verify that service method was called with correct arguments
        verify(archiveService, times(1)).archiveTable(tableName, archiveTableName);
    }
}