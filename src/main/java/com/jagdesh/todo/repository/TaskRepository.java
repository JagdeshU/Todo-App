package com.jagdesh.todo.repository;

import com.jagdesh.todo.entity.Task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByDescriptionContaining(String wordkey);

    @Override
    @Query("select t from Task t order by t.createdAt desc")
    List<Task> findAll();

    @Modifying
    @Query("update Task t set t.done = true where t.id = ?1")
    void completeTask(Long id);

    @Modifying
    @Query("update Task t set t.done = false where t.id = ?1")
    void uncompleteTask(Long id);

}
