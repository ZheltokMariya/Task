package com.example.task.model;

import javax.persistence.*;

@Entity
@Table(name = "number")
public class Number {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer value;

    public Number() {
    }

    public Number(Integer value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Number{" +
                "id=" + id +
                ", value=" + value +
                '}';
    }
}
