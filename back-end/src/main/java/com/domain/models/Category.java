package com.domain.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Munzir Masri on 10.8.2018.
 */
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "category")
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Transient
    private List<Task> tasks;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private WunderUser ownerUser;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public WunderUser getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(WunderUser ownerUser) {
        this.ownerUser = ownerUser;
    }
}
