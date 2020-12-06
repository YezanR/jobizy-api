package yezan.training.jobizyapi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JobTest {

    @Test
    public void addSkillRequirement() {
        Job job = new Job();
        job.addSkillRequirement(new SkillRequirement(new Skill("Python"), 10));
        assertEquals(1, job.getSkillRequirements().size());
        assertTrue(job.getSkillRequirements().contains(
                new SkillRequirement(new Skill("Python"), 10))
        );
    }
}
