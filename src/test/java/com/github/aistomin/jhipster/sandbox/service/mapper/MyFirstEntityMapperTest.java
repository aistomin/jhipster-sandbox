package com.github.aistomin.jhipster.sandbox.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class MyFirstEntityMapperTest {

    private MyFirstEntityMapper myFirstEntityMapper;

    @BeforeEach
    public void setUp() {
        myFirstEntityMapper = new MyFirstEntityMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(myFirstEntityMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(myFirstEntityMapper.fromId(null)).isNull();
    }
}
