package co.com.poli.managerProject.repositories;

import co.com.poli.managerProject.entities.ProjectTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectTaskRepository extends JpaRepository<ProjectTask, Long> {
    ProjectTask findProjectTaskByNameAndProjectIdentifier(String name, String projectIdentifier);
    ProjectTask findProjectTaskByProjectIdentifier(String projectIdentifier);
}
