package yezan.training.jobizyapi.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SkillTest {

    @Test
    public void equals_GivenSimilarObjects_ShouldReturnTrue() {
        assertEquals(new Skill("Python"), new Skill("Python"));
    }

    @Test
    public void equals_GivenSkillsWithDifferentCases_ShouldReturnTrue() {
        assertEquals(new Skill("PYTHon"), new Skill("python"));
    }
}
