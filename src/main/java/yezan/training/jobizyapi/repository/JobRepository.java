package yezan.training.jobizyapi.repository;

import yezan.training.jobizyapi.domain.Job;

import java.util.List;

public interface JobRepository {
    List<Job> findAll();
}
