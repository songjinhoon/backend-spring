package com.persoanltoy.backend.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.persoanltoy.backend.dummy.MemberDummyGenerate;
import com.persoanltoy.backend.dummy.OrderDummyGenerate;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Import(RestDocsConfig.class)
@AutoConfigureRestDocs
@Transactional
@AutoConfigureMockMvc
@SpringBootTest
@Disabled
public class BaseTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected EntityManager entityManager;

    @Autowired
    protected MemberDummyGenerate memberDummyGenerate;

    @Autowired
    protected OrderDummyGenerate orderDummyGenerate;

}
