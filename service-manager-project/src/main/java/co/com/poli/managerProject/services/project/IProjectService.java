package co.com.poli.managerProject.services.project;

import co.com.poli.managerProject.entities.Project;

import java.util.List;

public interface IProjectService {
    List<Project> findAll();

    void save(Project project);

    Project findProjectByProjectNameOrProjectIdentifier(String projectName, String projectIdentifier);

    List<Project> findProjectByProjectIdentifier(String projectName);

    Integer getTotalHoursOfProject(List<Project> project);

    Integer getTotalHourOfProjectByStatus(List<Project> project, String status);
}