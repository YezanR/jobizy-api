package yezan.training.jobizyapi.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.List;
import static org.mockito.Mockito.*;

import yezan.training.jobizyapi.domain.Job;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import yezan.training.jobizyapi.factory.CandidateFactory;
import yezan.training.jobizyapi.factory.JobFactory;
import yezan.training.jobizyapi.service.CandidateCrudService;
import yezan.training.jobizyapi.service.JobCrudService;
import yezan.training.jobizyapi.service.JobMatcher;

import javax.persistence.EntityNotFoundException;

@WebMvcTest
public class JobControllerTest {

    @MockBean
    JobMatcher jobMatcher;

    @MockBean
    CandidateCrudService candidateCrudService;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JobCrudService jobCrudService;

    @Test
    public void findAllMatchingJobs_shouldReturnJobs() throws Exception {

        List<Job> jobs = JobFactory.generateMany();
        when(jobMatcher.findAllMatchingJobs(any())).thenReturn(jobs);

        mockMvc.perform(get("/jobs/matchingCandidate/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{'title': 'IT Manager'}, {'title': 'Java developer'}, {'title': 'Angular tech lead'}]"));
    }

    @Test
    public void findAllMatchingJobs_GivenInvalidCandidate_ShouldReturnNotFoundStatus() throws Exception {
        when(candidateCrudService.findById(anyLong())).thenThrow(new EntityNotFoundException());

        mockMvc.perform(get("/jobs/matchingCandidate/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void applyForJob_ShouldReturnOK() throws Exception {
        when(candidateCrudService.findById(anyLong())).thenReturn(CandidateFactory.createDummy());
        when(jobCrudService.findById(anyLong())).thenReturn(new Job());

        mockMvc.perform(post("/jobs/12/apply/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void applyForJob_GivenInvalidJob_ShouldReturnNotFoundStatus() throws Exception {
        when(candidateCrudService.findById(anyLong())).thenReturn(CandidateFactory.createDummy());
        when(jobCrudService.findById(anyLong())).thenThrow(new EntityNotFoundException());

        mockMvc.perform(post("/jobs/12/apply/1"))
                .andExpect(status().isNotFound());
    }
}
