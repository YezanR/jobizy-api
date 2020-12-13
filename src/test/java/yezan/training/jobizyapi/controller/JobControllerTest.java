package yezan.training.jobizyapi.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.List;
import static org.mockito.Mockito.*;
import yezan.training.jobizyapi.domain.Job;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import yezan.training.jobizyapi.factory.JobFactory;
import yezan.training.jobizyapi.service.CandidateCrudService;
import yezan.training.jobizyapi.service.JobMatcher;

@WebMvcTest
public class JobControllerTest {

    @MockBean
    JobMatcher jobMatcher;

    @MockBean
    CandidateCrudService candidateCrudService;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findAllMatchingJobs() throws Exception {

        List<Job> jobs = JobFactory.generateMany();
        when(jobMatcher.findAllMatchingJobs(any())).thenReturn(jobs);

        mockMvc.perform(get("/jobs/matchingCandidate/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[{'title': 'IT Manager'}, {'title': 'Java developer'}, {'title': 'Angular tech lead'}]"));
    }
}
