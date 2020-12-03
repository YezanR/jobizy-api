package yezan.training.jobizyapi.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SkillTest {

    @Test
    public void equals() {
        assertEquals(new Skill("Python"), new Skill("Python"));
    }
}
