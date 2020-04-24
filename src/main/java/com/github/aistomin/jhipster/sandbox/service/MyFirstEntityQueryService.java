package com.github.aistomin.jhipster.sandbox.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import com.github.aistomin.jhipster.sandbox.domain.MyFirstEntity;
import com.github.aistomin.jhipster.sandbox.domain.*; // for static metamodels
import com.github.aistomin.jhipster.sandbox.repository.MyFirstEntityRepository;
import com.github.aistomin.jhipster.sandbox.service.dto.MyFirstEntityCriteria;
import com.github.aistomin.jhipster.sandbox.service.dto.MyFirstEntityDTO;
import com.github.aistomin.jhipster.sandbox.service.mapper.MyFirstEntityMapper;

/**
 * Service for executing complex queries for {@link MyFirstEntity} entities in the database.
 * The main input is a {@link MyFirstEntityCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link MyFirstEntityDTO} or a {@link Page} of {@link MyFirstEntityDTO} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class MyFirstEntityQueryService extends QueryService<MyFirstEntity> {

    private final Logger log = LoggerFactory.getLogger(MyFirstEntityQueryService.class);

    private final MyFirstEntityRepository myFirstEntityRepository;

    private final MyFirstEntityMapper myFirstEntityMapper;

    public MyFirstEntityQueryService(MyFirstEntityRepository myFirstEntityRepository, MyFirstEntityMapper myFirstEntityMapper) {
        this.myFirstEntityRepository = myFirstEntityRepository;
        this.myFirstEntityMapper = myFirstEntityMapper;
    }

    /**
     * Return a {@link List} of {@link MyFirstEntityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<MyFirstEntityDTO> findByCriteria(MyFirstEntityCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<MyFirstEntity> specification = createSpecification(criteria);
        return myFirstEntityMapper.toDto(myFirstEntityRepository.findAll(specification));
    }

    /**
     * Return a {@link Page} of {@link MyFirstEntityDTO} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<MyFirstEntityDTO> findByCriteria(MyFirstEntityCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<MyFirstEntity> specification = createSpecification(criteria);
        return myFirstEntityRepository.findAll(specification, page)
            .map(myFirstEntityMapper::toDto);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(MyFirstEntityCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<MyFirstEntity> specification = createSpecification(criteria);
        return myFirstEntityRepository.count(specification);
    }

    /**
     * Function to convert {@link MyFirstEntityCriteria} to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */
    protected Specification<MyFirstEntity> createSpecification(MyFirstEntityCriteria criteria) {
        Specification<MyFirstEntity> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getId(), MyFirstEntity_.id));
            }
            if (criteria.getStringField() != null) {
                specification = specification.and(buildStringSpecification(criteria.getStringField(), MyFirstEntity_.stringField));
            }
            if (criteria.getDateCreated() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getDateCreated(), MyFirstEntity_.dateCreated));
            }
            if (criteria.getSomeNumber() != null) {
                specification = specification.and(buildRangeSpecification(criteria.getSomeNumber(), MyFirstEntity_.someNumber));
            }
        }
        return specification;
    }
}
