package yezan.training.jobizyapi.service;

import org.junit.jupiter.api.Test;
import yezan.training.jobizyapi.domain.Job;
import yezan.training.jobizyapi.repository.JobRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JobCrudServiceTest {

    @Test
    public void findById_GivenId_ShouldReturnJob() {
        JobRepository jobRepository = mock(JobRepository.class);
        Job job = new Job();
        when(jobRepository.findById(anyLong())).thenReturn(Optional.of(job));

        JobCrudService jobCrudService = new JobCrudService(jobRepository);

        assertEquals(job, jobCrudService.findById(1));
    }

    @Test
    public void findById_GivenInvalidJob_ShouldThrowEntityNotFoundException() {
        JobRepository jobRepository = mock(JobRepository.class);
        when(jobRepository.findById(anyLong())).thenReturn(Optional.empty());

        JobCrudService jobCrudService = new JobCrudService(jobRepository);
        assertThrows(EntityNotFoundException.class, () -> jobCrudService.findById(1));
    }

    @Test
    public void create_GivenValidJob_ShouldAddItToRepositoryAndReturnIt() {
        JobRepository jobRepository = mock(JobRepository.class);
        Job job = new Job("Backend developer");
        when(jobRepository.save(any())).thenReturn(job);

        JobCrudService jobCrudService = new JobCrudService(jobRepository);
        Job createdJob = jobCrudService.create(job);

        verify(jobRepository, times(1)).save(job);
        assertEquals(job, createdJob);
    }

    @Test
    public void create_GivenInValidJob_ShouldThrowException() {
        JobCrudService jobCrudService = new JobCrudService(null);
        assertThrows(ConstraintViolationException.class, () -> jobCrudService.create(new Job()));
    }
}
