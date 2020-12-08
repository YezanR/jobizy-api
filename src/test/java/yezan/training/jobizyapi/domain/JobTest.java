package yezan.training.jobizyapi.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JobTest {

    @Test
    public void addSkillRequirement_ShouldAddIt() {
        Job job = new Job();
        job.addSkillRequirement(new SkillRequirement(new Skill("Python"), 10));
        assertEquals(1, job.getSkillRequirements().size());
        assertTrue(job.getSkillRequirements().contains(
                new SkillRequirement(new Skill("Python"), 10))
        );
    }

    @Test()
    public void addSkillRequirement_GivenRequirementWithSkillThatAlreadyExists_ShouldThrowException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Job job = new Job();
            job.addSkillRequirement(new SkillRequirement(new Skill("Python"), 10));

            job.addSkillRequirement(new SkillRequirement(new Skill("Python"), 16));
        });

        assertTrue(exception.getMessage().contains("Duplicate skill"));
    }
}
