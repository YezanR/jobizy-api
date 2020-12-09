package yezan.training.jobizyapi.service;

import org.springframework.stereotype.Service;
import yezan.training.jobizyapi.domain.Candidate;
import yezan.training.jobizyapi.repository.CandidateRepository;

import javax.persistence.EntityNotFoundException;

@Service
public class CandidateCrudService {

    private final CandidateRepository candidateRepository;

    public CandidateCrudService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Candidate findById(long id) {
        return candidateRepository.findById(id).orElseThrow(() ->
            new EntityNotFoundException("candidate with id " + id + " not found")
        );
    }
}
