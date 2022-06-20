package com.persoanltoy.backend.domain.todo.entity;

import com.persoanltoy.backend.domain.BaseEntity;
import com.persoanltoy.backend.domain.todo.dto.TodoInsertDto;
import com.persoanltoy.backend.domain.usr.entity.Usr;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(access = AccessLevel.PROTECTED)
@Entity @Getter
public class Todo extends BaseEntity {

    @Id
    @Column(name = "todo_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "usr_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Usr usr;

    private String title;

    private Boolean done;

    public static Todo empty() {
        return Todo.builder().build();
    }

    public static Todo create(TodoInsertDto todoInsertDto, Usr usr) {
        return Todo.builder()
                .title(todoInsertDto.getTitle())
                .done(todoInsertDto.getDone())
                .usr(usr)
                .build();
    }

}
