package org.example.resource;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.java.Log;
import org.example.repository.TodoRepository;
import org.example.resource.dto.TodoDTO;

import java.util.List;

@Log
@Path("todo")
public class TodoResource {

    @Inject
    private TodoRepository repository;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<TodoDTO> listTodos() {
        return repository.streamAll()
                .map(todo -> TodoDTO.fromEntity(todo))
                .toList();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Transactional
    public Response addTodo(final TodoDTO newTodo) {
        log.info(String.format("new todo: %s", newTodo.getDescription()));
        repository.persist(newTodo.toEntity());

        return Response.created(null).build(); // TODO location
    }

}
