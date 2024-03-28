package org.example.resource.dto;

import lombok.Builder;
import lombok.Data;
import org.example.model.Todo;

import java.time.LocalDateTime;

@Data
@Builder
public class TodoDTO {

    private Long id; // TODO UUID
    private String description;
    private LocalDateTime completedAt;

    public static TodoDTO fromEntity(final Todo todo) {
        return TodoDTO.builder()
                .id(todo.getId())
                .description(todo.getDescription())
                .completedAt(todo.getCompletedAt())
                .build();
    }

    public Todo toEntity() {
        return Todo.builder()
                .description(description)
                .build();
    }

}
