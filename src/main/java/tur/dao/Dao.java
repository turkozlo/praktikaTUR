package tur.dao;

import java.util.List;

public interface Dao<T> {
    List<T> getAll();

    boolean delete(T object);
}
