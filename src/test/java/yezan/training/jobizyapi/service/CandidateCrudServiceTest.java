package yezan.training.jobizyapi.service;

import org.junit.jupiter.api.Test;
import yezan.training.jobizyapi.domain.Candidate;
import yezan.training.jobizyapi.factory.CandidateFactory;
import yezan.training.jobizyapi.repository.CandidateRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CandidateCrudServiceTest {

    @Test
    public void findById_GivenId_ShouldFindCandidateFromRepo() {
        CandidateRepository candidateRepository = mock(CandidateRepository.class);
        when(candidateRepository.findById(any()))
                .thenReturn(Optional.of(CandidateFactory.createDummy()));
        CandidateCrudService crudService = new CandidateCrudService(candidateRepository);

        Candidate candidate = crudService.findById(1);

        assertEquals(CandidateFactory.createDummy(), candidate);
    }
}