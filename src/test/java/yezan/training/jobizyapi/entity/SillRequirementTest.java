package yezan.training.jobizyapi.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SillRequirementTest {

    @Test
    public void equals_GivenSimilarObjects_ShouldReturnTrue() {
        assertEquals(
                new SkillRequirement(new Skill("Java"), 5),
                new SkillRequirement(new Skill("Java"), 5)
        );
    }
}
