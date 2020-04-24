package com.github.aistomin.jhipster.sandbox.web.rest;

import com.github.aistomin.jhipster.sandbox.service.MyFirstEntityService;
import com.github.aistomin.jhipster.sandbox.web.rest.errors.BadRequestAlertException;
import com.github.aistomin.jhipster.sandbox.service.dto.MyFirstEntityDTO;
import com.github.aistomin.jhipster.sandbox.service.dto.MyFirstEntityCriteria;
import com.github.aistomin.jhipster.sandbox.service.MyFirstEntityQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.github.aistomin.jhipster.sandbox.domain.MyFirstEntity}.
 */
@RestController
@RequestMapping("/api")
public class MyFirstEntityResource {

    private final Logger log = LoggerFactory.getLogger(MyFirstEntityResource.class);

    private final MyFirstEntityService myFirstEntityService;

    private final MyFirstEntityQueryService myFirstEntityQueryService;

    public MyFirstEntityResource(MyFirstEntityService myFirstEntityService, MyFirstEntityQueryService myFirstEntityQueryService) {
        this.myFirstEntityService = myFirstEntityService;
        this.myFirstEntityQueryService = myFirstEntityQueryService;
    }

    /**
     * {@code GET  /my-first-entities} : get all the myFirstEntities.
     *
     * @param pageable the pagination information.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of myFirstEntities in body.
     */
    @GetMapping("/my-first-entities")
    public ResponseEntity<List<MyFirstEntityDTO>> getAllMyFirstEntities(MyFirstEntityCriteria criteria, Pageable pageable) {
        log.debug("REST request to get MyFirstEntities by criteria: {}", criteria);
        Page<MyFirstEntityDTO> page = myFirstEntityQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /my-first-entities/count} : count all the myFirstEntities.
     *
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
     */
    @GetMapping("/my-first-entities/count")
    public ResponseEntity<Long> countMyFirstEntities(MyFirstEntityCriteria criteria) {
        log.debug("REST request to count MyFirstEntities by criteria: {}", criteria);
        return ResponseEntity.ok().body(myFirstEntityQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /my-first-entities/:id} : get the "id" myFirstEntity.
     *
     * @param id the id of the myFirstEntityDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the myFirstEntityDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/my-first-entities/{id}")
    public ResponseEntity<MyFirstEntityDTO> getMyFirstEntity(@PathVariable Long id) {
        log.debug("REST request to get MyFirstEntity : {}", id);
        Optional<MyFirstEntityDTO> myFirstEntityDTO = myFirstEntityService.findOne(id);
        return ResponseUtil.wrapOrNotFound(myFirstEntityDTO);
    }
}
