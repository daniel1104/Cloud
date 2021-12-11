package co.com.poli.managerProject.services.projectTask;

import co.com.poli.managerProject.entities.Backlog;
import co.com.poli.managerProject.entities.ProjectTask;
import co.com.poli.managerProject.repositories.IProjectTaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProjectTaskServiceImpl implements IProjectTaskService {

    private IProjectTaskRepository iProjectTaskRepository;

    @Override
    public List<ProjectTask> findAll() {
        return iProjectTaskRepository.findAll();
    }

    @Override
    public void save(ProjectTask projectTask) {
        iProjectTaskRepository.save(projectTask);
    }

    @Override
    public ProjectTask findProjectTaskByNameAndProjectIdentifier(String name, String projectIdentifier) {
        return iProjectTaskRepository.findProjectTaskByNameAndProjectIdentifier(name, projectIdentifier);
    }

    @Override
    public ProjectTask findProjectTaskByProjectIdentifier(String projectIdentifier) {
        return iProjectTaskRepository.findProjectTaskByProjectIdentifier(projectIdentifier);
    }
}
