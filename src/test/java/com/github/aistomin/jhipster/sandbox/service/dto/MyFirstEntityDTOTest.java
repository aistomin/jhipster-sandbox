package com.github.aistomin.jhipster.sandbox.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.github.aistomin.jhipster.sandbox.web.rest.TestUtil;

public class MyFirstEntityDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MyFirstEntityDTO.class);
        MyFirstEntityDTO myFirstEntityDTO1 = new MyFirstEntityDTO();
        myFirstEntityDTO1.setId(1L);
        MyFirstEntityDTO myFirstEntityDTO2 = new MyFirstEntityDTO();
        assertThat(myFirstEntityDTO1).isNotEqualTo(myFirstEntityDTO2);
        myFirstEntityDTO2.setId(myFirstEntityDTO1.getId());
        assertThat(myFirstEntityDTO1).isEqualTo(myFirstEntityDTO2);
        myFirstEntityDTO2.setId(2L);
        assertThat(myFirstEntityDTO1).isNotEqualTo(myFirstEntityDTO2);
        myFirstEntityDTO1.setId(null);
        assertThat(myFirstEntityDTO1).isNotEqualTo(myFirstEntityDTO2);
    }
}
