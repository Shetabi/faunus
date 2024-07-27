package com.faunus.api.core.owner;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class OwnerRepositoryTest {

    @Autowired
    private OwnerRepository sut;

    @Test
    void ownerCanBeCreated() {
        Owner owner = new Owner(null, "Test Owner");

        Owner saved = sut.save(owner);

        assertNotNull(saved.getId());
    }
}
