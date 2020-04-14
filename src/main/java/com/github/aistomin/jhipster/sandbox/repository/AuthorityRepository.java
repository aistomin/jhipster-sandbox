package com.github.aistomin.jhipster.sandbox.repository;

import com.github.aistomin.jhipster.sandbox.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
