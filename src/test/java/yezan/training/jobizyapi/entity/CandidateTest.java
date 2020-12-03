package yezan.training.jobizyapi.entity;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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
    public void addExperience_GivenSkillAndMonthsOfExperience_ShouldAddAsExperience() {
        Candidate candidate = new Candidate();
        candidate.addExperience(new Skill("PHP"), 45);

        Experience expectedExperience = new Experience(new Skill("PHP"), 45);
        assertEquals(candidate.getExperiences().size(), 1);
        assertTrue(candidate.getExperiences().contains(expectedExperience));
    }

    @Test
    public void matches_GivenJobWithRequirementsAndCandidateWithMatchingExperiences_ShouldReturnTrue() {
        Job job = new Job();
        Set<SkillRequirement> requirements = new HashSet<>(Arrays.asList(
                new SkillRequirement(new Skill("Java"), 36),
                new SkillRequirement(new Skill("Python"), 12),
                new SkillRequirement(new Skill("Jira"), 8)
        ));
        job.setSkillRequirements(requirements);
        Candidate candidate = new Candidate();
        candidate.addExperience(new Skill("Java"), 50);
        candidate.addExperience(new Skill("Python"), 36);
        candidate.addExperience(new Skill("Jira"), 12);

        assertTrue(candidate.matches(job));
    }
}
