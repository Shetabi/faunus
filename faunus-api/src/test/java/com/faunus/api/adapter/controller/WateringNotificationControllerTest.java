package com.faunus.api.adapter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.faunus.api.core.watering.WateringScheduleService;
import com.faunus.api.core.watering.WateringSubscriptionStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = {WateringNotificationController.class})
class WateringNotificationControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private WateringScheduleService wateringScheduleService;


    @Test
    void whenNoErrorCreatedStatusIsReturned() throws Exception {

        mockMvc.perform(post("/owners/1/watering-notifications/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper
                                .writeValueAsString(new WateringNotificationController.NotificationRequest("token"))))
                .andExpect(status().isCreated());
    }

    @Test
    void whenUnsubscribedNoContentStatusIsReturned() throws Exception {
        mockMvc.perform(delete("/owners/1/watering-notifications/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void getSubscriptionStatusIsMappedCorrectly() throws Exception {
        when(wateringScheduleService.getSubscriptionStatus(any(), any()))
                .thenReturn(WateringSubscriptionStatus.SUBSCRIBED);

        mockMvc.perform(get("/owners/1/watering-notifications/2")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("SUBSCRIBED")));
    }


}