package co.com.poli.managerProject.repositories;

import co.com.poli.managerProject.entities.Backlog;
import co.com.poli.managerProject.entities.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBacklogRepository extends JpaRepository<Backlog, Long> {
    Backlog findBacklogByProjectIdentifier(String projectIdentifier);
    Backlog findBacklogByProjectOrderById(Project project);
}
