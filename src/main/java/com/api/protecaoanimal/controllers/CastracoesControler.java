package com.api.protecaoanimal.controllers;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.protecaoanimal.dtos.CastracoesDto;
import com.api.protecaoanimal.models.CastracoesModel;
import com.api.protecaoanimal.services.CastracoesService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@Tag(name = "Castração")
@RequestMapping("/castracao")
public class CastracoesControler {

    final CastracoesService castracoesService;

    public CastracoesControler(CastracoesService castracoesService){
        this.castracoesService = castracoesService;
    }

    @PostMapping
    @Operation(summary = "Cadastrar uma nova ação", description = "Cadastrar uma nova ação" )
    public ResponseEntity<Object> savecastracoes(@RequestBody @Valid CastracoesDto castracoesDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(castracoesService.save(castracoesDto));
    }

    @GetMapping
    @Operation(summary = "Listar todas as ações", description = "Listar todas as ações")
    public ResponseEntity<Object> getListcastracoes(){
        return ResponseEntity.status(HttpStatus.OK).body(castracoesService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Exibir ação por ID", description = "Exibir uma determinada Ação pelo seu ID")
    public ResponseEntity<Object> getcastracoes(@PathVariable UUID id){
        return ResponseEntity.status(HttpStatus.OK).body(castracoesService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar ação", description = "Atualiza uma determinada Ação passando o seu ID e as configurações que deseja")
    public ResponseEntity<Object> updatecastracoes(@PathVariable("id") UUID id, @RequestBody @Valid CastracoesDto castracoesDto){
        Optional<CastracoesModel> castracoesModelOptional = castracoesService.findById(id);
        if (!castracoesModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontado");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(castracoesService.save(castracoesDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta uma ação", description = "Deleta uma determinada Ação passando o seu ID")
    public ResponseEntity<Object> deletecastracoes(@PathVariable("id") UUID id){
        Optional<CastracoesModel> castracoesModelOptional = castracoesService.findById(id);
        if (!castracoesModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item não encontado");
        }
        castracoesService.delete(castracoesModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Deletado com successo.");
    }

}
