package com.moskalev.controller;

import com.moskalev.domain.dto.DtoObject;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CrudController<IN, OUT> {

    void create(IN in);

    void delete(Integer id);

    OUT read(Integer id);

    Page<OUT> readAll();

    void update(Integer id, IN in);


}
