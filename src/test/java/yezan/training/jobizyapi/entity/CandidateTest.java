package yezan.training.jobizyapi.entity;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CandidateTest {

    @Test
    public void addExperience() {
        Candidate candidate = new Candidate();
        Experience experience = new Experience(new Skill("Java"), 50);
        candidate.addExperience(
                experience
        );

        assertEquals(candidate.getExperiences().size(), 1);
        assertTrue(candidate.getExperiences().contains(experience));
    }

    @Test
    public void matchesJob() {
        Job job = new Job();
        Set<SkillRequirement> requirements = new HashSet<>(Arrays.asList(
                new SkillRequirement(new Skill("Java"), 36),
                new SkillRequirement(new Skill("Python"), 12),
                new SkillRequirement(new Skill("Jira"), 8)
        ));
        job.setSkillRequirements(requirements);
        Candidate candidate = new Candidate();
        candidate.addExperience(new Experience(new Skill("Java"), 50));
        candidate.addExperience(new Experience(new Skill("Python"), 36));
        candidate.addExperience(new Experience(new Skill("Jira"), 12));

        assertTrue(candidate.matches(job));
    }
}
