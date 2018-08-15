package com.domain.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Munzır Masri on 8.8.2018.
 */
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date createDate = new Date();
    private Date dueDate;
    private Date remindingDate;
    private Boolean isCompleted = false;
    private Date completionDate;
    private String note;

    @NotNull
    @ManyToOne
//    @Transient
    private Category category;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<SubTask> subTasks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
//        return null;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getRemindingDate() {
        return remindingDate;
    }

    public void setRemindingDate(Date remindingDate) {
        this.remindingDate = remindingDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public List<SubTask> getSubTasks() {
        return subTasks;
//        return null;
    }

    public void setSubTasks(List<SubTask> subTasks) {
        this.subTasks = subTasks;
    }

    public void addSubTask(SubTask subTask) {
        if (subTasks == null) {
            subTasks = new ArrayList<>();
        }
        subTasks.add(subTask);
    }


}
