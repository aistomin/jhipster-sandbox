package com.github.aistomin.jhipster.sandbox.web.rest;

import com.github.aistomin.jhipster.sandbox.JhipsterApp;
import com.github.aistomin.jhipster.sandbox.domain.MyFirstEntity;
import com.github.aistomin.jhipster.sandbox.repository.MyFirstEntityRepository;
import com.github.aistomin.jhipster.sandbox.service.MyFirstEntityService;
import com.github.aistomin.jhipster.sandbox.service.dto.MyFirstEntityDTO;
import com.github.aistomin.jhipster.sandbox.service.mapper.MyFirstEntityMapper;
import com.github.aistomin.jhipster.sandbox.service.dto.MyFirstEntityCriteria;
import com.github.aistomin.jhipster.sandbox.service.MyFirstEntityQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link MyFirstEntityResource} REST controller.
 */
@SpringBootTest(classes = JhipsterApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class MyFirstEntityResourceIT {

    private static final String DEFAULT_STRING_FIELD = "AAAAAAAAAA";
    private static final String UPDATED_STRING_FIELD = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_CREATED = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_CREATED = LocalDate.now(ZoneId.systemDefault());
    private static final LocalDate SMALLER_DATE_CREATED = LocalDate.ofEpochDay(-1L);

    private static final Integer DEFAULT_SOME_NUMBER = 1;
    private static final Integer UPDATED_SOME_NUMBER = 2;
    private static final Integer SMALLER_SOME_NUMBER = 1 - 1;

    @Autowired
    private MyFirstEntityRepository myFirstEntityRepository;

    @Autowired
    private MyFirstEntityMapper myFirstEntityMapper;

    @Autowired
    private MyFirstEntityService myFirstEntityService;

    @Autowired
    private MyFirstEntityQueryService myFirstEntityQueryService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restMyFirstEntityMockMvc;

    private MyFirstEntity myFirstEntity;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MyFirstEntity createEntity(EntityManager em) {
        MyFirstEntity myFirstEntity = new MyFirstEntity()
            .stringField(DEFAULT_STRING_FIELD)
            .dateCreated(DEFAULT_DATE_CREATED)
            .someNumber(DEFAULT_SOME_NUMBER);
        return myFirstEntity;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static MyFirstEntity createUpdatedEntity(EntityManager em) {
        MyFirstEntity myFirstEntity = new MyFirstEntity()
            .stringField(UPDATED_STRING_FIELD)
            .dateCreated(UPDATED_DATE_CREATED)
            .someNumber(UPDATED_SOME_NUMBER);
        return myFirstEntity;
    }

    @BeforeEach
    public void initTest() {
        myFirstEntity = createEntity(em);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntities() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList
        restMyFirstEntityMockMvc.perform(get("/api/my-first-entities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(myFirstEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].stringField").value(hasItem(DEFAULT_STRING_FIELD)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED.toString())))
            .andExpect(jsonPath("$.[*].someNumber").value(hasItem(DEFAULT_SOME_NUMBER)));
    }
    
    @Test
    @Transactional
    public void getMyFirstEntity() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get the myFirstEntity
        restMyFirstEntityMockMvc.perform(get("/api/my-first-entities/{id}", myFirstEntity.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(myFirstEntity.getId().intValue()))
            .andExpect(jsonPath("$.stringField").value(DEFAULT_STRING_FIELD))
            .andExpect(jsonPath("$.dateCreated").value(DEFAULT_DATE_CREATED.toString()))
            .andExpect(jsonPath("$.someNumber").value(DEFAULT_SOME_NUMBER));
    }


    @Test
    @Transactional
    public void getMyFirstEntitiesByIdFiltering() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        Long id = myFirstEntity.getId();

        defaultMyFirstEntityShouldBeFound("id.equals=" + id);
        defaultMyFirstEntityShouldNotBeFound("id.notEquals=" + id);

        defaultMyFirstEntityShouldBeFound("id.greaterThanOrEqual=" + id);
        defaultMyFirstEntityShouldNotBeFound("id.greaterThan=" + id);

        defaultMyFirstEntityShouldBeFound("id.lessThanOrEqual=" + id);
        defaultMyFirstEntityShouldNotBeFound("id.lessThan=" + id);
    }


    @Test
    @Transactional
    public void getAllMyFirstEntitiesByStringFieldIsEqualToSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where stringField equals to DEFAULT_STRING_FIELD
        defaultMyFirstEntityShouldBeFound("stringField.equals=" + DEFAULT_STRING_FIELD);

        // Get all the myFirstEntityList where stringField equals to UPDATED_STRING_FIELD
        defaultMyFirstEntityShouldNotBeFound("stringField.equals=" + UPDATED_STRING_FIELD);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesByStringFieldIsNotEqualToSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where stringField not equals to DEFAULT_STRING_FIELD
        defaultMyFirstEntityShouldNotBeFound("stringField.notEquals=" + DEFAULT_STRING_FIELD);

        // Get all the myFirstEntityList where stringField not equals to UPDATED_STRING_FIELD
        defaultMyFirstEntityShouldBeFound("stringField.notEquals=" + UPDATED_STRING_FIELD);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesByStringFieldIsInShouldWork() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where stringField in DEFAULT_STRING_FIELD or UPDATED_STRING_FIELD
        defaultMyFirstEntityShouldBeFound("stringField.in=" + DEFAULT_STRING_FIELD + "," + UPDATED_STRING_FIELD);

        // Get all the myFirstEntityList where stringField equals to UPDATED_STRING_FIELD
        defaultMyFirstEntityShouldNotBeFound("stringField.in=" + UPDATED_STRING_FIELD);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesByStringFieldIsNullOrNotNull() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where stringField is not null
        defaultMyFirstEntityShouldBeFound("stringField.specified=true");

        // Get all the myFirstEntityList where stringField is null
        defaultMyFirstEntityShouldNotBeFound("stringField.specified=false");
    }
                @Test
    @Transactional
    public void getAllMyFirstEntitiesByStringFieldContainsSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where stringField contains DEFAULT_STRING_FIELD
        defaultMyFirstEntityShouldBeFound("stringField.contains=" + DEFAULT_STRING_FIELD);

        // Get all the myFirstEntityList where stringField contains UPDATED_STRING_FIELD
        defaultMyFirstEntityShouldNotBeFound("stringField.contains=" + UPDATED_STRING_FIELD);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesByStringFieldNotContainsSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where stringField does not contain DEFAULT_STRING_FIELD
        defaultMyFirstEntityShouldNotBeFound("stringField.doesNotContain=" + DEFAULT_STRING_FIELD);

        // Get all the myFirstEntityList where stringField does not contain UPDATED_STRING_FIELD
        defaultMyFirstEntityShouldBeFound("stringField.doesNotContain=" + UPDATED_STRING_FIELD);
    }


    @Test
    @Transactional
    public void getAllMyFirstEntitiesByDateCreatedIsEqualToSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where dateCreated equals to DEFAULT_DATE_CREATED
        defaultMyFirstEntityShouldBeFound("dateCreated.equals=" + DEFAULT_DATE_CREATED);

        // Get all the myFirstEntityList where dateCreated equals to UPDATED_DATE_CREATED
        defaultMyFirstEntityShouldNotBeFound("dateCreated.equals=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesByDateCreatedIsNotEqualToSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where dateCreated not equals to DEFAULT_DATE_CREATED
        defaultMyFirstEntityShouldNotBeFound("dateCreated.notEquals=" + DEFAULT_DATE_CREATED);

        // Get all the myFirstEntityList where dateCreated not equals to UPDATED_DATE_CREATED
        defaultMyFirstEntityShouldBeFound("dateCreated.notEquals=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesByDateCreatedIsInShouldWork() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where dateCreated in DEFAULT_DATE_CREATED or UPDATED_DATE_CREATED
        defaultMyFirstEntityShouldBeFound("dateCreated.in=" + DEFAULT_DATE_CREATED + "," + UPDATED_DATE_CREATED);

        // Get all the myFirstEntityList where dateCreated equals to UPDATED_DATE_CREATED
        defaultMyFirstEntityShouldNotBeFound("dateCreated.in=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesByDateCreatedIsNullOrNotNull() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where dateCreated is not null
        defaultMyFirstEntityShouldBeFound("dateCreated.specified=true");

        // Get all the myFirstEntityList where dateCreated is null
        defaultMyFirstEntityShouldNotBeFound("dateCreated.specified=false");
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesByDateCreatedIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where dateCreated is greater than or equal to DEFAULT_DATE_CREATED
        defaultMyFirstEntityShouldBeFound("dateCreated.greaterThanOrEqual=" + DEFAULT_DATE_CREATED);

        // Get all the myFirstEntityList where dateCreated is greater than or equal to UPDATED_DATE_CREATED
        defaultMyFirstEntityShouldNotBeFound("dateCreated.greaterThanOrEqual=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesByDateCreatedIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where dateCreated is less than or equal to DEFAULT_DATE_CREATED
        defaultMyFirstEntityShouldBeFound("dateCreated.lessThanOrEqual=" + DEFAULT_DATE_CREATED);

        // Get all the myFirstEntityList where dateCreated is less than or equal to SMALLER_DATE_CREATED
        defaultMyFirstEntityShouldNotBeFound("dateCreated.lessThanOrEqual=" + SMALLER_DATE_CREATED);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesByDateCreatedIsLessThanSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where dateCreated is less than DEFAULT_DATE_CREATED
        defaultMyFirstEntityShouldNotBeFound("dateCreated.lessThan=" + DEFAULT_DATE_CREATED);

        // Get all the myFirstEntityList where dateCreated is less than UPDATED_DATE_CREATED
        defaultMyFirstEntityShouldBeFound("dateCreated.lessThan=" + UPDATED_DATE_CREATED);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesByDateCreatedIsGreaterThanSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where dateCreated is greater than DEFAULT_DATE_CREATED
        defaultMyFirstEntityShouldNotBeFound("dateCreated.greaterThan=" + DEFAULT_DATE_CREATED);

        // Get all the myFirstEntityList where dateCreated is greater than SMALLER_DATE_CREATED
        defaultMyFirstEntityShouldBeFound("dateCreated.greaterThan=" + SMALLER_DATE_CREATED);
    }


    @Test
    @Transactional
    public void getAllMyFirstEntitiesBySomeNumberIsEqualToSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where someNumber equals to DEFAULT_SOME_NUMBER
        defaultMyFirstEntityShouldBeFound("someNumber.equals=" + DEFAULT_SOME_NUMBER);

        // Get all the myFirstEntityList where someNumber equals to UPDATED_SOME_NUMBER
        defaultMyFirstEntityShouldNotBeFound("someNumber.equals=" + UPDATED_SOME_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesBySomeNumberIsNotEqualToSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where someNumber not equals to DEFAULT_SOME_NUMBER
        defaultMyFirstEntityShouldNotBeFound("someNumber.notEquals=" + DEFAULT_SOME_NUMBER);

        // Get all the myFirstEntityList where someNumber not equals to UPDATED_SOME_NUMBER
        defaultMyFirstEntityShouldBeFound("someNumber.notEquals=" + UPDATED_SOME_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesBySomeNumberIsInShouldWork() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where someNumber in DEFAULT_SOME_NUMBER or UPDATED_SOME_NUMBER
        defaultMyFirstEntityShouldBeFound("someNumber.in=" + DEFAULT_SOME_NUMBER + "," + UPDATED_SOME_NUMBER);

        // Get all the myFirstEntityList where someNumber equals to UPDATED_SOME_NUMBER
        defaultMyFirstEntityShouldNotBeFound("someNumber.in=" + UPDATED_SOME_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesBySomeNumberIsNullOrNotNull() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where someNumber is not null
        defaultMyFirstEntityShouldBeFound("someNumber.specified=true");

        // Get all the myFirstEntityList where someNumber is null
        defaultMyFirstEntityShouldNotBeFound("someNumber.specified=false");
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesBySomeNumberIsGreaterThanOrEqualToSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where someNumber is greater than or equal to DEFAULT_SOME_NUMBER
        defaultMyFirstEntityShouldBeFound("someNumber.greaterThanOrEqual=" + DEFAULT_SOME_NUMBER);

        // Get all the myFirstEntityList where someNumber is greater than or equal to UPDATED_SOME_NUMBER
        defaultMyFirstEntityShouldNotBeFound("someNumber.greaterThanOrEqual=" + UPDATED_SOME_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesBySomeNumberIsLessThanOrEqualToSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where someNumber is less than or equal to DEFAULT_SOME_NUMBER
        defaultMyFirstEntityShouldBeFound("someNumber.lessThanOrEqual=" + DEFAULT_SOME_NUMBER);

        // Get all the myFirstEntityList where someNumber is less than or equal to SMALLER_SOME_NUMBER
        defaultMyFirstEntityShouldNotBeFound("someNumber.lessThanOrEqual=" + SMALLER_SOME_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesBySomeNumberIsLessThanSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where someNumber is less than DEFAULT_SOME_NUMBER
        defaultMyFirstEntityShouldNotBeFound("someNumber.lessThan=" + DEFAULT_SOME_NUMBER);

        // Get all the myFirstEntityList where someNumber is less than UPDATED_SOME_NUMBER
        defaultMyFirstEntityShouldBeFound("someNumber.lessThan=" + UPDATED_SOME_NUMBER);
    }

    @Test
    @Transactional
    public void getAllMyFirstEntitiesBySomeNumberIsGreaterThanSomething() throws Exception {
        // Initialize the database
        myFirstEntityRepository.saveAndFlush(myFirstEntity);

        // Get all the myFirstEntityList where someNumber is greater than DEFAULT_SOME_NUMBER
        defaultMyFirstEntityShouldNotBeFound("someNumber.greaterThan=" + DEFAULT_SOME_NUMBER);

        // Get all the myFirstEntityList where someNumber is greater than SMALLER_SOME_NUMBER
        defaultMyFirstEntityShouldBeFound("someNumber.greaterThan=" + SMALLER_SOME_NUMBER);
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultMyFirstEntityShouldBeFound(String filter) throws Exception {
        restMyFirstEntityMockMvc.perform(get("/api/my-first-entities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(myFirstEntity.getId().intValue())))
            .andExpect(jsonPath("$.[*].stringField").value(hasItem(DEFAULT_STRING_FIELD)))
            .andExpect(jsonPath("$.[*].dateCreated").value(hasItem(DEFAULT_DATE_CREATED.toString())))
            .andExpect(jsonPath("$.[*].someNumber").value(hasItem(DEFAULT_SOME_NUMBER)));

        // Check, that the count call also returns 1
        restMyFirstEntityMockMvc.perform(get("/api/my-first-entities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultMyFirstEntityShouldNotBeFound(String filter) throws Exception {
        restMyFirstEntityMockMvc.perform(get("/api/my-first-entities?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restMyFirstEntityMockMvc.perform(get("/api/my-first-entities/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingMyFirstEntity() throws Exception {
        // Get the myFirstEntity
        restMyFirstEntityMockMvc.perform(get("/api/my-first-entities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }
}
