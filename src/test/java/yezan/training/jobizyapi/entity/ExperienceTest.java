package yezan.training.jobizyapi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExperienceTest {

    @Test
    public void equals_GivenSimilarObjects_ShouldReturnTrue() {
        assertEquals(
                new Experience(new Skill("Java"), 36),
                new Experience(new Skill("Java"), 36)
        );
    }
}
