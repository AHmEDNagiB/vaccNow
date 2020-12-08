package com.naguib.technicalTasks.vaccNow.controller;

import com.google.gson.Gson;
import com.naguib.technicalTasks.vaccNow.VaccNowApplication;
import com.naguib.technicalTasks.vaccNow.controller.dto.ResponseDTO;
import com.naguib.technicalTasks.vaccNow.services.AvailableTimeSlotsService;
import com.naguib.technicalTasks.vaccNow.services.BranchCRUDService;
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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = VaccNowApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BranchControllerTest {


    @Mock
    private BranchCRUDService branchCRUDService;
    @Mock
    private AvailableTimeSlotsService availableTimeSlotsService;
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;
    Gson gson = new Gson();

    @InjectMocks
    @Autowired
    private BranchController branchController;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test_gatBranches_noSentParam() throws Exception {
        when(branchCRUDService.gatBranches(anyLong(), anyLong())).thenReturn(new ResponseDTO("valid response"));
        mockMvc.perform(get("/branch").contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    public void test_gatBranches_sentParam() throws Exception {
        when(branchCRUDService.gatBranches(anyLong(), anyLong())).thenReturn(new ResponseDTO("valid response"));
        mockMvc.perform(get("/branch").contentType(APPLICATION_JSON).param("branch", "1"))
                .andExpect(status().isOk()).andReturn();

    }

    @Test
    public void test_gatBranches_invalidResponse() throws Exception {
        when(branchCRUDService.gatBranches(anyLong(), anyLong())).thenReturn(new ResponseDTO("invalid response", 204));
        mockMvc.perform(get("/branch").contentType(APPLICATION_JSON).param("branch", "1"))
                .andExpect(status().isNoContent()).andReturn();

    }

    @Test
    public void test_gatAvailableTimeSlots_noSentParam() throws Exception {
        when(availableTimeSlotsService.gatAvailableTimeSlots(anyLong(), any())).thenReturn(new ResponseDTO("valid response"));
        mockMvc.perform(get("/branch/available-time-slots")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn();

    }

    @Test
    public void test_gatAvailableTimeSlots_sentParam() throws Exception {
        when(availableTimeSlotsService.gatAvailableTimeSlots(anyLong(), any())).thenReturn(new ResponseDTO("valid response"));
        mockMvc.perform(get("/branch/available-time-slots")
                .contentType(APPLICATION_JSON).param("branch", "2")
                .param("date", "1"))
                .andExpect(status().isOk()).andReturn();

    }
}