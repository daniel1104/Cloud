package co.com.poli.managerProject.services.backlog;

import co.com.poli.managerProject.entities.Backlog;
import co.com.poli.managerProject.entities.Project;
import co.com.poli.managerProject.repositories.IBacklogRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BacklogServiceImpl implements IBacklogService {

    private IBacklogRepository backlogRepository;

    @Override
    public List<Backlog> findAll() {
        return backlogRepository.findAll();
    }

    @Override
    public void save(Backlog backlog) {
        backlogRepository.save(backlog);
    }

    @Override
    public Backlog findBacklogByProjectIdentifier(String projectIdentifier) {
        return backlogRepository.findBacklogByProjectIdentifier(projectIdentifier);
    }

    @Override
    public Backlog findBacklogByProjectOrderById(Project project) {
        return backlogRepository.findBacklogByProjectOrderById(project);
    }
}
