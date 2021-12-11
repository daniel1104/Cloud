package co.com.poli.managerProject.services.project;

import co.com.poli.managerProject.entities.Project;
import co.com.poli.managerProject.entities.ProjectTask;
import co.com.poli.managerProject.repositories.IProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service()
@AllArgsConstructor
public class ProjectServiceImpl implements IProjectService{

    private final IProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Override
    public Project findProjectByProjectNameOrProjectIdentifier(String projectName, String projectIdentifier) {
        return projectRepository.findProjectByProjectNameOrProjectIdentifier(projectName, projectIdentifier);
    }

    @Override
    public List<Project> findProjectByProjectIdentifier(String projectName) {
        return projectRepository.findProjectByProjectIdentifier(projectName);
    }

    public Integer getTotalHoursOfProject(List<Project> project) {
        return project.stream()
                .map(projects -> projects.getBacklog().getProjectTasks())
                .flatMap(List::stream)
                .map(ProjectTask::getHours)
                .reduce((double) 0, Double::sum)
                .intValue();
    }

    public Integer getTotalHourOfProjectByStatus(List<Project> project, String status) {
        return project.stream()
                .map(projects -> projects.getBacklog().getProjectTasks())
                .flatMap(List::stream)
                .filter(projectTask -> Objects.equals(projectTask.getStatus(), status))
                .map(ProjectTask::getHours)
                .reduce((double) 0, Double::sum)
                .intValue();
    }
}
