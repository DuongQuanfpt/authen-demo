package com.example.internservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.internservice.dto.intern.InternDTO;
import com.example.internservice.dto.intern.InternDTOAdd;
import com.example.internservice.dto.intern.InternDTOUpdate;
import com.example.internservice.service.impl.InternService;

@RestController
public class InternController {

    @Autowired
    private InternService internService;

    @GetMapping(value = "/intern")
    public List<InternDTO> getAll(
            @RequestParam(name = "page", required = true) int page,
            @RequestParam(name = "size", required = true) int size,
            @RequestParam(name = "sort", required = false, defaultValue = "id") String sort) {

        return internService.getAll(page, size, sort);
    }

    @GetMapping(value = "/intern/{id}")
    public InternDTO getById(@PathVariable Long id) {

        return internService.getById(id);
    }

    @PostMapping(value = "/intern/add")
    public InternDTO add(@Valid @RequestBody InternDTOAdd dto) {
        return internService.save(dto);
    }

    @PutMapping(value = "/intern/update/{id}")
    public InternDTO update(@PathVariable Long id,
            @RequestBody InternDTOUpdate dtoUpdate) {

        return internService.update(id,dtoUpdate);
    }

    @DeleteMapping(value = "/intern/delete/{id}")
    @Secured("Admin")
    public InternDTO delete(@PathVariable Long id) {

        return internService.delete(id);
    }

}
