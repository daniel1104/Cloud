package co.com.poli.managerProject.controllers;

import co.com.poli.managerProject.dto.ProjectTaskDto;
import co.com.poli.managerProject.entities.Project;
import co.com.poli.managerProject.entities.ProjectTask;
import com.example.multimodule.service.helpers.FormatMessage;
import com.example.multimodule.service.helpers.Response;
import com.example.multimodule.service.helpers.ResponseBuilder;
import co.com.poli.managerProject.services.project.IProjectService;
import co.com.poli.managerProject.services.projectTask.IProjectTaskService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class ProjectTaskController {

    private final IProjectTaskService projectTaskService;
    private IProjectService projectService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<Response> findAll(HttpServletRequest request) {
        List<ProjectTask> projectTasks = projectTaskService.findAll();

        if (projectTasks.isEmpty()) {
            return new ResponseEntity<>(ResponseBuilder.failed("Not are project tasks", request.getRequestURI()),
                    HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(ResponseBuilder.success(projectTasks, request.getRequestURI()),
                HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Response> save(@Valid @RequestBody ProjectTaskDto projectTaskDto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(ResponseBuilder.failed(FormatMessage.formatMessage(result), request.getRequestURI()),
                    HttpStatus.BAD_REQUEST);
        }

        ProjectTask task =  projectTaskService.findProjectTaskByNameAndProjectIdentifier(projectTaskDto.getName(), projectTaskDto.getProjectIdentifier());
        ProjectTask taskIdentifier = projectTaskService.findProjectTaskByProjectIdentifier(projectTaskDto.getProjectIdentifier());

        if(task != null || taskIdentifier != null) {
            return new ResponseEntity<>(ResponseBuilder.failed("El identificador del proyecto ya existe para este nombre", request.getRequestURI()),
                    HttpStatus.BAD_REQUEST);
        }

        ProjectTask projectTask = modelMapper.map(projectTaskDto, ProjectTask.class);
        projectTaskService.save(projectTask);

        return new ResponseEntity<>(ResponseBuilder.successCreated(projectTask, request.getRequestURI()),
                HttpStatus.CREATED);
    }

    @GetMapping("/project/{projectIdentifier}")
    public ResponseEntity<Response> findProjectTasks(@PathVariable String projectIdentifier, HttpServletRequest request) {
        List<Project> projects = projectService.findProjectByProjectIdentifier(projectIdentifier);

        if (projects.isEmpty()) {
            return new ResponseEntity<>(ResponseBuilder.failed("Not are projects", request.getRequestURI()),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(ResponseBuilder.success(projects.get(0).getBacklog().getProjectTasks(), request.getRequestURI()),
                HttpStatus.OK);
    }

    @GetMapping("/hours/project/{projectIdentifier}")
    public ResponseEntity<Response> getTotalHoursForProject(@PathVariable String projectIdentifier, HttpServletRequest request) {
        List<Project> projects = projectService.findProjectByProjectIdentifier(projectIdentifier);

       if (projects.isEmpty()) {
            return new ResponseEntity<>(ResponseBuilder.failed("Not are projects", request.getRequestURI()),
                    HttpStatus.NOT_FOUND);
        }

        Integer totalHoursProject = projectService.getTotalHoursOfProject(projects);

        return new ResponseEntity<>(ResponseBuilder.success(totalHoursProject, request.getRequestURI()),
                HttpStatus.OK);
    }

    @GetMapping("/hours/project/{projectIdentifier}/{status}")
    public ResponseEntity<Response> getTotalProjectHoursForStatus(@PathVariable String projectIdentifier, @PathVariable String status, HttpServletRequest request) {
        List<Project> projects = projectService.findProjectByProjectIdentifier(projectIdentifier);

        if (projects.isEmpty()) {
            return new ResponseEntity<>(ResponseBuilder.failed("Not are projects", request.getRequestURI()),
                    HttpStatus.NOT_FOUND);
        }

        Integer totalHoursProject = projectService.getTotalHourOfProjectByStatus(projects, status);

        return new ResponseEntity<>(ResponseBuilder.success(totalHoursProject, request.getRequestURI()),
                HttpStatus.OK);
    }

    @PutMapping("/{idTask}/{projectIdentifier}")
    public ResponseEntity<Response> deleteTask(@PathVariable Long idTask, @PathVariable String projectIdentifier, HttpServletRequest request) {
        List<Project> projects = projectService.findProjectByProjectIdentifier(projectIdentifier);

        if (projects.isEmpty()) {
            return new ResponseEntity<>(ResponseBuilder.failed("Not are projects", request.getRequestURI()),
                    HttpStatus.NOT_FOUND);
        }

        List<ProjectTask> projectTasks =  projects.get(0).getBacklog().getProjectTasks().stream()
                .filter(projectTask -> Objects.equals(projectTask.getId(), idTask))
                .peek(projectTask -> {
                    projectTask.setStatus("deleted");
                    projectTaskService.save(projectTask);
                }).collect(Collectors.toList());

        return new ResponseEntity<>(ResponseBuilder.success(projectTasks.get(0), request.getRequestURI()),
                HttpStatus.OK);
    }
}
