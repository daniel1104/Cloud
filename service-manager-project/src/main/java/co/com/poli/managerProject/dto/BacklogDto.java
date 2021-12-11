package co.com.poli.managerProject.dto;

import co.com.poli.managerProject.entities.Project;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BacklogDto {

    @NotBlank(message = "Identificador del proyecto no puede estar vacio")
    private String projectIdentifier;

    @NotNull(message = "Tienes que asociar el backlog con un proyecto existente")
    private Project project;
}
