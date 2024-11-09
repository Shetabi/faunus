package com.faunus.api.core.owner;

import com.faunus.api.BaseTestSpringBoot;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class OwnerRepositoryTest extends BaseTestSpringBoot {

    @Autowired
    private OwnerRepository sut;

    @Test
    void ownerCanBeCreated() {
        Owner owner = new Owner(null, "Test Owner");

        Owner saved = sut.save(owner);

        assertNotNull(saved.getId());
    }
}
