package com.github.aistomin.jhipster.sandbox.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.github.aistomin.jhipster.sandbox.web.rest.TestUtil;

public class MyFirstEntityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MyFirstEntity.class);
        MyFirstEntity myFirstEntity1 = new MyFirstEntity();
        myFirstEntity1.setId(1L);
        MyFirstEntity myFirstEntity2 = new MyFirstEntity();
        myFirstEntity2.setId(myFirstEntity1.getId());
        assertThat(myFirstEntity1).isEqualTo(myFirstEntity2);
        myFirstEntity2.setId(2L);
        assertThat(myFirstEntity1).isNotEqualTo(myFirstEntity2);
        myFirstEntity1.setId(null);
        assertThat(myFirstEntity1).isNotEqualTo(myFirstEntity2);
    }
}
