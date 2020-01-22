package com.twu.biblioteca.repository;

import com.twu.biblioteca.model.Model;

import java.util.List;

public interface Repository<M extends Model> {
    List<M> all();
}
