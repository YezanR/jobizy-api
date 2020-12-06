package yezan.training.jobizyapi.factory;

import yezan.training.jobizyapi.entity.Candidate;

import java.time.LocalDate;

public class CandidateFactory {

    private CandidateFactory() {

    }

    public static Candidate createDummy() {
        return new Candidate(
                "John",
                "Doe",
                "john.doe@example.com",
                LocalDate.of(1993, 12, 12)
        );
    }
}
