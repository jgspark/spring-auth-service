package com.example.content.config

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.jdbc.core.JdbcOperations
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.transaction.annotation.Propagation
import org.springframework.transaction.annotation.Transactional
import javax.sql.DataSource

@Transactional(value = "mainTransactionManager", propagation = Propagation.NOT_SUPPORTED)
abstract class AbstractJobTestConfiguration : AbstractBaseJobTestConfiguration() {

    @Autowired
    @Qualifier("mainDataSource")
    private lateinit var dataSource: DataSource

    private lateinit var jdbcTemplate: JdbcOperations

    @BeforeEach
    fun init() {
        this.jdbcTemplate = JdbcTemplate(dataSource)
    }
}

