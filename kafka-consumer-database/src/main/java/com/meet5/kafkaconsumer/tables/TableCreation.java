package com.meet5.kafkaconsumer.tables;

import com.meet5.kafkaconsumer.service.TableCreationService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class TableCreation {
    private final TableCreationService tableCreationService;
    private final JdbcTemplate jdbcTemplate;

    public TableCreation(TableCreationService tableCreationService, JdbcTemplate jdbcTemplate) {
        this.tableCreationService = tableCreationService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void initializeTables() {
        tableCreationService.createTables(jdbcTemplate);
    }
}
