package co.com.poli.managerProject.services.backlog;

import co.com.poli.managerProject.entities.Backlog;
import co.com.poli.managerProject.entities.Project;

import java.util.List;

public interface IBacklogService {
    List<Backlog> findAll();
    void save(Backlog backlog);
    Backlog findBacklogByProjectIdentifier(String projectIdentifier);
    Backlog findBacklogByProjectOrderById(Project project);
}
