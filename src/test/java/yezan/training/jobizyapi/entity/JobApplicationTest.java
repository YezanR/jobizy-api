package yezan.training.jobizyapi.entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class JobApplicationTest {

    @Test
    public void equals_GivenSameObjects_ShouldReturnTrue() {
        JobApplication jobApplication1 = new JobApplication(
                new Candidate("John", "Doe", "john.doe@example.com"),
                new Job("IT manager")
        );

        JobApplication jobApplication2 = new JobApplication(
                new Candidate("John", "Doe", "john.doe@example.com"),
                new Job("IT manager")
        );

        assertEquals(jobApplication1, jobApplication2);
    }
}
