package com.example.productservice.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("h2-test")
public class ArchiveRepositoryTest {

    @Autowired
    private ArchiveRepository archiveRepository;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @Test
    public void testArchiveTable_Success() {
        // Arrange: Define source and archive table names
        String sourceTableName = "zoom";
        String archiveTableName = "zoom_archive";

        // Act: Call the archiveTable method
        archiveRepository.archiveTable(sourceTableName, archiveTableName);

        // Assert: Verify that the archive and delete queries were executed
        String archiveQuery = "INSERT INTO " + archiveTableName + " SELECT * FROM " + sourceTableName + " WHERE duration IS NOT NULL";
        String deleteQuery = "DELETE FROM " + sourceTableName + " WHERE duration IS NOT NULL";

        verify(jdbcTemplate, times(1)).update(archiveQuery);
        verify(jdbcTemplate, times(1)).update(deleteQuery);
    }

    @Test
    @Sql(scripts = "/data.sql") // Load data.sql before executing the test
    public void testArchiveTable_Success1() {
        // Act: Run the archiving operation
        archiveRepository.archiveTable("zoom", "zoom_archive");

        // Assert: Verify that the expected records have been moved to the archive table
        Integer sourceTableCount = jdbcTemplate.queryForObject("SELECT * FROM zoom", Integer.class);
        Integer archiveTableCount = jdbcTemplate.queryForObject("SELECT * FROM zoom_archive", Integer.class);

        assertEquals(Integer.valueOf(2), sourceTableCount); // Only 3 records remaining in the zoom table
        //assertEquals(Integer.valueOf(3), archiveTableCount); // 2 records in the zoom archive table
    }
}
