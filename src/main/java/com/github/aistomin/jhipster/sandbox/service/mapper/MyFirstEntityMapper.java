package com.github.aistomin.jhipster.sandbox.service.mapper;


import com.github.aistomin.jhipster.sandbox.domain.*;
import com.github.aistomin.jhipster.sandbox.service.dto.MyFirstEntityDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link MyFirstEntity} and its DTO {@link MyFirstEntityDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MyFirstEntityMapper extends EntityMapper<MyFirstEntityDTO, MyFirstEntity> {



    default MyFirstEntity fromId(Long id) {
        if (id == null) {
            return null;
        }
        MyFirstEntity myFirstEntity = new MyFirstEntity();
        myFirstEntity.setId(id);
        return myFirstEntity;
    }
}
