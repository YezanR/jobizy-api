package yezan.training.jobizyapi.repository;

import yezan.training.jobizyapi.entity.Job;

import java.util.List;

public interface JobRepository {
    List<Job> findAll();
}
