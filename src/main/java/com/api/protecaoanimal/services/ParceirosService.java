package com.api.protecaoanimal.services;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.api.protecaoanimal.dtos.ParceirosDto;
import com.api.protecaoanimal.exceptions.ItemNotFoundException;
import com.api.protecaoanimal.models.ParceirosModel;
import com.api.protecaoanimal.repositories.ParceirosRepository;

import jakarta.transaction.Transactional;

@Service
public class ParceirosService {
    
    final ParceirosRepository parceirosRepository;

    public ParceirosService(ParceirosRepository parceirosRepository) {
        this.parceirosRepository = parceirosRepository;
    }

    @Transactional
    public ParceirosModel save(ParceirosDto parceirosDto) {
        var parceirosModel = new ParceirosModel();
        BeanUtils.copyProperties(parceirosDto, parceirosModel);
        parceirosModel.setRegistro(LocalDateTime.now(ZoneId.of("UTC")));
        return parceirosRepository.save(parceirosModel);
    }

    public List<ParceirosModel> findAll() {
        return parceirosRepository.findAll();
    }

    public ParceirosModel findById(UUID id) {
        Optional<ParceirosModel> parceirosModel = parceirosRepository.findById(id);
        return parceirosModel.orElseThrow(ItemNotFoundException::new);
    }

    public void delete(ParceirosModel parceirosModel) {
        findById(parceirosModel.getId());
        parceirosRepository.delete(parceirosModel);
    }

}
