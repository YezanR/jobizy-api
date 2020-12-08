package yezan.training.jobizyapi.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import yezan.training.jobizyapi.factory.CandidateFactory;

public class JobApplicationTest {

    @Test
    public void equals_GivenSameObjects_ShouldReturnTrue() {
        JobApplication jobApplication1 = new JobApplication(
                CandidateFactory.createDummy(),
                new Job("IT manager")
        );

        JobApplication jobApplication2 = new JobApplication(
                CandidateFactory.createDummy(),
                new Job("IT manager")
        );

        assertEquals(jobApplication1, jobApplication2);
    }
}
