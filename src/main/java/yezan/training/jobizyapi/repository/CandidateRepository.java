package yezan.training.jobizyapi.repository;

import yezan.training.jobizyapi.domain.Candidate;

public interface CandidateRepository {
    Candidate findById(long id);
}
