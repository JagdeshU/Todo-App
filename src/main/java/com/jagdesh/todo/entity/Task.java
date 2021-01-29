package com.jagdesh.todo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Data
public class Task {

    @Id
    @ApiModelProperty(notes = "Task's Identity", required = true)
    @SequenceGenerator(name = "taskGenerator", sequenceName = "task_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "taskGenerator", strategy = GenerationType.SEQUENCE)
    @Column(name = "Task_ID")
    private Long id;

    @ApiModelProperty(notes = "Description of Task", required = true)
    @NotBlank(message = "Description is Mandatory")
    @Column(name = "Description")
    private String description;

    @ApiModelProperty(notes = "Status of Task", required = true)
    @Column(name = "Status_of_Task")
    private Boolean done = false;

    @ApiModelProperty(notes = "Date of Task Created", required = true)
    @JsonProperty(value = "created_at", required = false)
    @Column(name = "Created_Date_Time", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    //  setters for Boolean - done

    public void completeTask(){
        this.setDone(true);
    }

    public void uncompleteTask(){
        this.setDone(false);
    }

}
