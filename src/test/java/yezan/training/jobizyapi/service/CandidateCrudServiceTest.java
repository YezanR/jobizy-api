package yezan.training.jobizyapi.service;

import org.junit.jupiter.api.Test;
import yezan.training.jobizyapi.domain.Candidate;
import yezan.training.jobizyapi.factory.CandidateFactory;
import yezan.training.jobizyapi.repository.CandidateRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CandidateCrudServiceTest {

    @Test
    public void findById_GivenId_ShouldFindCandidateFromRepo() {

        CandidateRepository candidateRepository = new FakeCandidateRepository();
        CandidateCrudService crudService = new CandidateCrudService(candidateRepository);

        Candidate candidate = crudService.findById(1);

        assertEquals(CandidateFactory.createDummy(), candidate);
    }
}

class FakeCandidateRepository implements CandidateRepository {

    @Override
    public Candidate findById(long id) {
        return CandidateFactory.createDummy();
    }
}
