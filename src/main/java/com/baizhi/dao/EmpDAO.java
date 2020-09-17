package com.baizhi.dao;

import com.baizhi.entity.Emp;

import java.util.List;

public interface EmpDAO {

    void save(Emp emp);

    List<Emp> findAll();

    void delete(String id);

    Emp find(String id);

    void update(Emp emp);
}
