package yezan.training.jobizyapi.entity;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import yezan.training.jobizyapi.exception.JobApplicationException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CandidateTest {

    @Test
    public void addExperience_GivenExperience_ShouldAddIt() {
        Candidate candidate = new Candidate();
        Experience experience = new Experience(new Skill("Java"), 50);
        candidate.addExperience(
                experience
        );

        assertEquals(candidate.getExperiences().size(), 1);
        assertTrue(candidate.getExperiences().contains(experience));
    }

    @Test
    public void addExperience_GivenSkillAndMonthsOfExperience_ShouldAddIt() {
        Candidate candidate = new Candidate();
        candidate.addExperience(new Skill("PHP"), 45);

        Experience expectedExperience = new Experience(new Skill("PHP"), 45);
        assertEquals(candidate.getExperiences().size(), 1);
        assertTrue(candidate.getExperiences().contains(expectedExperience));
    }

    @Test
    public void matchesJob_GivenJobWithRequirementsAndCandidateWithMatchingExperiences_ShouldReturnTrue() {
        Job job = new Job();
        Set<SkillRequirement> requirements = new HashSet<>(Arrays.asList(
                new SkillRequirement(new Skill("Java"), 36),
                new SkillRequirement(new Skill("Python"), 12),
                new SkillRequirement(new Skill("Jira"), 8)
        ));
        job.addAllSkillRequirement(requirements);
        Candidate candidate = new Candidate();
        candidate.addExperience(new Skill("Java"), 50);
        candidate.addExperience(new Skill("Python"), 36);
        candidate.addExperience(new Skill("Jira"), 12);

        assertTrue(candidate.matchesJob(job));
    }

    @Test
    public void matchesJob_GivenJobWithRequirementsAndCandidateWithLessSkills_ShouldReturnFalse() {
        Job job = new Job();
        Set<SkillRequirement> requirements = new HashSet<>(Arrays.asList(
                new SkillRequirement(new Skill("Java"), 36),
                new SkillRequirement(new Skill("Python"), 12),
                new SkillRequirement(new Skill("Jira"), 8)
        ));
        job.addAllSkillRequirement(requirements);
        Candidate candidate = new Candidate();
        candidate.addExperience(new Skill("Java"), 50);
        candidate.addExperience(new Skill("Python"), 36);

        assertFalse(candidate.matchesJob(job));
    }

    @Test
    public void matchesJob_GivenJobWithRequirementsAndCandidateWithLessMonthsOfExperience_ShouldReturnFalse() {
        Job job = new Job();
        Set<SkillRequirement> requirements = new HashSet<>(Arrays.asList(
                new SkillRequirement(new Skill("Java"), 36),
                new SkillRequirement(new Skill("Python"), 12),
                new SkillRequirement(new Skill("Jira"), 8)
        ));
        job.addAllSkillRequirement(requirements);
        Candidate candidate = new Candidate();
        candidate.addExperience(new Skill("Java"), 50);
        candidate.addExperience(new Skill("Python"), 8);
        new SkillRequirement(new Skill("Jira"), 12);

        assertFalse(candidate.matchesJob(job));
    }

    @Test
    public void applyForJob_GivenMatchingJob_ShouldReturnJobApplication() {
        Candidate candidate = new Candidate("john", "doe", "john.doe@example.com");
        Job job = new Job("Lead Java developer");

        JobApplication actualJobApplication = candidate.applyForJob(job);

        JobApplication expectedJobApplication = new JobApplication(candidate, job);
        assertEquals(expectedJobApplication, actualJobApplication);
    }

    @Test
    public void applyForJob_GivenNonMatchingJob_ShouldThrowException() {
        Throwable exception = assertThrows(JobApplicationException.class, () -> {
            Candidate candidate = new Candidate("john", "doe", "john.doe@example.com");
            Job job = new Job("Lead Java developer");
            job.addSkillRequirement(new SkillRequirement(new Skill("Python"), 36));

            candidate.applyForJob(job);
        });

        assertTrue(exception.getMessage().toLowerCase().contains("job requirements not met"));
    }
}
