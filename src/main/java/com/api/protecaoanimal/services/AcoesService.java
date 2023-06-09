package com.api.protecaoanimal.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.api.protecaoanimal.dtos.AcoesDto;
import com.api.protecaoanimal.exceptions.ItemNotFoundException;
import com.api.protecaoanimal.models.AcoesModel;
import com.api.protecaoanimal.repositories.AcoesRepository;

import jakarta.transaction.Transactional;

@Service
public class AcoesService {
    
    final AcoesRepository acoesRepository;

    public AcoesService(AcoesRepository acoesRepository) {
        this.acoesRepository = acoesRepository;
    }

    @Transactional
    public AcoesModel save(AcoesDto acoesDto) {
        var acoesModel = new AcoesModel();
        BeanUtils.copyProperties(acoesDto, acoesModel);
        acoesModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return acoesRepository.save(acoesModel);
    }

    public AcoesModel update(UUID id, AcoesDto acoesDto) {
        var acoesModel = new AcoesModel();
        BeanUtils.copyProperties(acoesDto, acoesModel);
        acoesModel.setId(id);
        acoesModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return acoesRepository.save(acoesModel);
    }

    public Page<AcoesModel> findAll(Pageable pageable) {
        return acoesRepository.findAll(pageable);
    }

    public AcoesModel findById(UUID id) {
        Optional<AcoesModel> acoesModel = acoesRepository.findById(id);
        return acoesModel.orElseThrow(() -> new ItemNotFoundException("UUID de ação não existe"));
    }

    public void delete(AcoesModel acoesModel) {
        findById(acoesModel.getId());
        acoesRepository.delete(acoesModel);
    }

}
