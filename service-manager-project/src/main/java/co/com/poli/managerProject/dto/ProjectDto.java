package co.com.poli.managerProject.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class ProjectDto {

    @NotBlank(message = "Nombre del proyecto no puede estar vacio")
    private String projectName;

    @Length(min = 5, max = 7, message = "El identificador del proyecto no puede tener menos de 5 caracteres y mas de 7")
    @NotBlank(message = "Identificador del proyecto no puede estar vacio")
    private String projectIdentifier;

    @NotBlank(message = "Descripcion no puede estar vacia")
    private String description;

    private Date startDate;
    private Date endDate;
}
