package com.github.aistomin.jhipster.sandbox.service;

import com.github.aistomin.jhipster.sandbox.domain.MyFirstEntity;
import com.github.aistomin.jhipster.sandbox.repository.MyFirstEntityRepository;
import com.github.aistomin.jhipster.sandbox.service.dto.MyFirstEntityDTO;
import com.github.aistomin.jhipster.sandbox.service.mapper.MyFirstEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link MyFirstEntity}.
 */
@Service
@Transactional
public class MyFirstEntityService {

    private final Logger log = LoggerFactory.getLogger(MyFirstEntityService.class);

    private final MyFirstEntityRepository myFirstEntityRepository;

    private final MyFirstEntityMapper myFirstEntityMapper;

    public MyFirstEntityService(MyFirstEntityRepository myFirstEntityRepository, MyFirstEntityMapper myFirstEntityMapper) {
        this.myFirstEntityRepository = myFirstEntityRepository;
        this.myFirstEntityMapper = myFirstEntityMapper;
    }

    /**
     * Save a myFirstEntity.
     *
     * @param myFirstEntityDTO the entity to save.
     * @return the persisted entity.
     */
    public MyFirstEntityDTO save(MyFirstEntityDTO myFirstEntityDTO) {
        log.debug("Request to save MyFirstEntity : {}", myFirstEntityDTO);
        MyFirstEntity myFirstEntity = myFirstEntityMapper.toEntity(myFirstEntityDTO);
        myFirstEntity = myFirstEntityRepository.save(myFirstEntity);
        return myFirstEntityMapper.toDto(myFirstEntity);
    }

    /**
     * Get all the myFirstEntities.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<MyFirstEntityDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MyFirstEntities");
        return myFirstEntityRepository.findAll(pageable)
            .map(myFirstEntityMapper::toDto);
    }

    /**
     * Get one myFirstEntity by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<MyFirstEntityDTO> findOne(Long id) {
        log.debug("Request to get MyFirstEntity : {}", id);
        return myFirstEntityRepository.findById(id)
            .map(myFirstEntityMapper::toDto);
    }

    /**
     * Delete the myFirstEntity by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete MyFirstEntity : {}", id);
        myFirstEntityRepository.deleteById(id);
    }
}
