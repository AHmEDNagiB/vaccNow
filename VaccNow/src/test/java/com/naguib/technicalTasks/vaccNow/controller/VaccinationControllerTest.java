package com.naguib.technicalTasks.vaccNow.controller;


import com.google.gson.Gson;
import com.naguib.technicalTasks.vaccNow.VaccNowApplication;
import com.naguib.technicalTasks.vaccNow.controller.dto.ResponseDTO;
import com.naguib.technicalTasks.vaccNow.controller.dto.ScheduleVaccinationRequestDTO;
import com.naguib.technicalTasks.vaccNow.services.VaccinationService;
import com.naguib.technicalTasks.vaccNow.utils.PayingType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = VaccNowApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class VaccinationControllerTest {

    @Mock
    private VaccinationService vaccinationService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    Gson gson = new Gson();

    @InjectMocks
    @Autowired
    private VaccinationController vaccinationController;

    ScheduleVaccinationRequestDTO requestDTO;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        requestDTO = new ScheduleVaccinationRequestDTO();
    }

    @Test
    public void test_scheduleVaccination_sendNoBody() throws Exception {
        mockMvc.perform(post("/vaccination/schedule").contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void test_scheduleVaccination_sendInvalidBody() throws Exception {
        requestDTO.setClintEmail(null);
        requestDTO.setTimeSlot(0);
        mockMvc.perform(post("/vaccination/schedule").contentType(APPLICATION_JSON)
                .content(gson.toJson(requestDTO)))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void test_scheduleVaccination_sendvalidBody() throws Exception {
        when(vaccinationService.scheduleVaccination(any())).thenReturn(new ResponseDTO("valid response"));
        requestDTO.setClintEmail("a@a.com");
        requestDTO.setTimeSlot(1);
        requestDTO.setBranchVaccine(1);
        requestDTO.setPayingType(PayingType.Cash);
        requestDTO.setVaccinationDay("2020-12-22");
        mockMvc.perform(post("/vaccination/schedule").contentType(APPLICATION_JSON)
                .content(gson.toJson(requestDTO)))
                .andExpect(status().isOk()).andReturn();
    }

}