package com.example.internservice.service;

import java.util.List;

import com.example.internservice.dto.intern.InternDTO;
import com.example.internservice.dto.intern.InternDTOAdd;
import com.example.internservice.dto.intern.InternDTOUpdate;

public interface IInternService {
    public InternDTO save(InternDTOAdd dtoAdd);
    public List<InternDTO> getAll(int page, int size, String sortParam);
    public InternDTO update(Long id, InternDTOUpdate dtoUpdate);
    public InternDTO delete(Long id);
    public InternDTO getById(Long id);
    public String getActivateCode(Long id);
    public void getCodeByRate();
    public String active(String code);

}
