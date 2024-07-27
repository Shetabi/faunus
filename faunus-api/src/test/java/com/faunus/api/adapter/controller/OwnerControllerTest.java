package com.faunus.api.adapter.controller;

import com.faunus.api.core.owner.Owner;
import com.faunus.api.core.owner.OwnerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {OwnerController.class})
class OwnerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OwnerRepository ownerRepository;


    @Test
    void ownerIsReturnedIfExists() throws Exception {

        when(ownerRepository.findByName(matches("jdoe")))
                .thenReturn(Optional.of(new Owner(1L, "jdoe")));

        mockMvc.perform(get("/owners/jdoe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("jdoe")));
    }

    @Test
    void notFoundReturnsIfOwnerNotFound() throws Exception {

        when(ownerRepository.findByName(matches("jdoe")))
                .thenReturn(Optional.empty());

        mockMvc.perform(get("/owners/jdoe"))
                .andExpect(status().isNotFound());
    }

}