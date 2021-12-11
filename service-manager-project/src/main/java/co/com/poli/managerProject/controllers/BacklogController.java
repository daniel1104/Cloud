package co.com.poli.managerProject.controllers;

import co.com.poli.managerProject.dto.BacklogDto;
import co.com.poli.managerProject.entities.Backlog;
import com.example.multimodule.service.helpers.FormatMessage;
import com.example.multimodule.service.helpers.Response;
import co.com.poli.managerProject.services.backlog.IBacklogService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

import com.example.multimodule.service.helpers.ResponseBuilder;

@RestController
@RequestMapping("/backlog")
@AllArgsConstructor
public class BacklogController {

    private final IBacklogService iBacklogService;
    private final ModelMapper modelMapper;

    @GetMapping()
    public ResponseEntity<Response> findAll(HttpServletRequest request) {
        List<Backlog> backlog = iBacklogService.findAll();

        if (backlog.isEmpty()) {
            return new ResponseEntity<>(ResponseBuilder.failed("Not are backlogs", request.getRequestURI()),
                    HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(ResponseBuilder.success(backlog, request.getRequestURI()),
                HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Response> save(@Valid @RequestBody BacklogDto backlogDto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(ResponseBuilder.failed(FormatMessage.formatMessage(result), request.getRequestURI()),
                    HttpStatus.BAD_REQUEST);
        } else {
            Backlog backlog = iBacklogService.findBacklogByProjectIdentifier(backlogDto.getProjectIdentifier());
            Backlog backlogProject = iBacklogService.findBacklogByProjectOrderById(backlogDto.getProject());

            if (backlog != null || backlogProject == null) {
                return new ResponseEntity<>(ResponseBuilder.failed("El identificador ya existe o No existe el proyecto ingresado", request.getRequestURI()),
                        HttpStatus.BAD_REQUEST);
            }
        }

        Backlog backlog = modelMapper.map(backlogDto, Backlog.class); // transformacion de dto a entidad
        iBacklogService.save(backlog);

        return new ResponseEntity<>(ResponseBuilder.successCreated(backlog, request.getRequestURI()),
                HttpStatus.CREATED);
    }
}
