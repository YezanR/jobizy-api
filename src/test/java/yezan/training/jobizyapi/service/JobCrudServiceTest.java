package yezan.training.jobizyapi.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import yezan.training.jobizyapi.domain.Job;
import yezan.training.jobizyapi.domain.Skill;
import yezan.training.jobizyapi.domain.SkillRequirement;
import yezan.training.jobizyapi.factory.JobFactory;
import yezan.training.jobizyapi.repository.JobRepository;
import yezan.training.jobizyapi.repository.SkillRequirementRepository;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JobCrudServiceTest {

    private JobCrudService jobCrudService;
    private JobRepository jobRepository;
    private SkillRequirementRepository skillRequirementRepository;

    @BeforeEach
    protected void setUp() {
        jobRepository = mock(JobRepository.class);
        skillRequirementRepository = mock(SkillRequirementRepository.class);
        jobCrudService = new JobCrudService(jobRepository, skillRequirementRepository);
    }

    @Test
    public void findById_GivenId_ShouldReturnJob() {
        Job job = new Job();
        when(jobRepository.findById(anyLong())).thenReturn(Optional.of(job));

        assertEquals(job, jobCrudService.findById(1));
    }

    @Test
    public void findById_GivenInvalidJob_ShouldThrowEntityNotFoundException() {
        when(jobRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> jobCrudService.findById(1));
    }

    @Test
    public void create_GivenValidJob_ShouldAddItToRepositoryAndReturnIt() {
        Job job = new Job("Backend developer");
        when(jobRepository.save(any())).thenReturn(job);

        Job createdJob = jobCrudService.create(job);

        verify(jobRepository, times(1)).save(job);
        assertEquals(job, createdJob);
    }

    @Test
    public void create_GivenInValidJob_ShouldThrowException() {
        assertThrows(ConstraintViolationException.class, () -> jobCrudService.create(new Job()));
    }

    @Test
    public void update_GivenValidJob_ShouldUpdateItInRepositoryAndReturnIt() {
        Job job = new Job(12L, "Frontend developer");
        when(jobRepository.save(any())).thenReturn(job);

        Job updatedJob = jobCrudService.update(job);

        verify(jobRepository, times(1)).save(job);
        assertEquals(job, updatedJob);
    }

    @Test
    public void update_GivenInValidJob_ShouldThrowException() {
        assertThrows(ConstraintViolationException.class, () -> jobCrudService.update(new Job("Product owner")));
    }

    @Test
    public void delete_GivenJobId_ShouldDeleteJobWithAllRelatedEntities() {
        long jobId = 12L;
        Job job = JobFactory.createWithSkillRequirements(jobId, "Java developer",
                new SkillRequirement(1L, new Skill("Java"), 36),
                new SkillRequirement(2L, new Skill("SQL"), 14)
        );

        jobCrudService.delete(job);

        verify(jobRepository, times(1)).delete(job);
        verify(skillRequirementRepository, times(1))
                .deleteByIdIn(Arrays.asList(1L, 2L));
    }
}
