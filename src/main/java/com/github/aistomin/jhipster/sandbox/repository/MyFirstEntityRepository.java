package com.github.aistomin.jhipster.sandbox.repository;

import com.github.aistomin.jhipster.sandbox.domain.MyFirstEntity;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the MyFirstEntity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MyFirstEntityRepository extends JpaRepository<MyFirstEntity, Long>, JpaSpecificationExecutor<MyFirstEntity> {
}
