package yezan.training.jobizyapi.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import yezan.training.jobizyapi.entity.Candidate;
import yezan.training.jobizyapi.entity.Job;
import yezan.training.jobizyapi.entity.Skill;
import yezan.training.jobizyapi.entity.SkillRequirement;
import yezan.training.jobizyapi.factory.CandidateFactory;
import yezan.training.jobizyapi.factory.JobFactory;
import yezan.training.jobizyapi.repository.JobRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JobMatcherTest {

    @Test
    public void findAllMatchingJobs_GivenSomeJobsAndCandidate_ShouldReturnMatchingJobs() {

        Candidate candidate = CandidateFactory.createDummy();
        candidate.addExperience(new Skill("Java"), 42);
        candidate.addExperience(new Skill("Angular"), 42);
        candidate.addExperience(new Skill("Kubernetes"), 2);
        candidate.addExperience(new Skill("SQL"), 24);

        JobRepository jobRepository = new FakeJobRepository();
        JobMatcher jobMatcher = new JobMatcher(jobRepository);
        List<Job> jobs = jobMatcher.findAllMatchingJobs(candidate);

        assertEquals(Collections.singletonList(
                JobFactory.createWithSkillRequirements("Java developer",
                        new SkillRequirement(new Skill("Java"), 36),
                        new SkillRequirement(new Skill("SQL"), 14)
                )
        ), jobs);
    }
}

class FakeJobRepository implements JobRepository {

    @Override
    public List<Job> findAll() {
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