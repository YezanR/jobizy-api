package yezan.training.jobizyapi.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import yezan.training.jobizyapi.domain.*;
import yezan.training.jobizyapi.factory.CandidateFactory;
import yezan.training.jobizyapi.factory.JobFactory;
import yezan.training.jobizyapi.repository.JobRepository;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class JobMatcherTest {

    @Mock
    JobRepository jobRepository;

    @InjectMocks
    JobMatcher jobMatcher;

    @Test
    public void findAllMatchingJobs_GivenSomeJobsAndCandidate_ShouldReturnMatchingJobs() {

        Candidate candidate = CandidateFactory.createDummy();
        candidate.addExperience(new Skill("Java"), 42);
        candidate.addExperience(new Skill("Angular"), 42);
        candidate.addExperience(new Skill("Kubernetes"), 2);
        candidate.addExperience(new Skill("SQL"), 24);

        when(jobRepository.findAll()).thenReturn(jobsForFindAllMatchingJob());

        List<Job> jobs = jobMatcher.findAllMatchingJobs(candidate);

        assertEquals(Collections.singletonList(
                JobFactory.createWithSkillRequirements("Java developer",
                        new SkillRequirement(new Skill("Java"), 36),
                        new SkillRequirement(new Skill("SQL"), 14)
                )
        ), jobs);
    }

    private List<Job> jobsForFindAllMatchingJob() {
        ArrayList<Job> jobs = new ArrayList<>();

        jobs.add(
                JobFactory.createWithSkillRequirements("IT Manager",
                        new SkillRequirement(new Skill("Project management"), 36),
                        new SkillRequirement(new Skill("English")))
        );
        jobs.add(
                JobFactory.createWithSkillRequirements("Java developer",
                        new SkillRequirement(new Skill("Java"), 36),
                        new SkillRequirement(new Skill("SQL"), 14))
        );
        jobs.add(
                JobFactory.createWithSkillRequirements("Angular tech lead",
                        new SkillRequirement(new Skill("Angular"), 48),
                        new SkillRequirement(new Skill("HTML"), 36))
        );

        return jobs;
    }
}