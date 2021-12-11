package co.com.poli.managerProject.services.projectTask;
import co.com.poli.managerProject.entities.Backlog;
import co.com.poli.managerProject.entities.ProjectTask;

import java.util.List;

public interface IProjectTaskService {
    List<ProjectTask> findAll();
    void save(ProjectTask projectTask);
    ProjectTask findProjectTaskByNameAndProjectIdentifier(String name, String projectIdentifier);
    ProjectTask findProjectTaskByProjectIdentifier(String projectIdentifier);
}
