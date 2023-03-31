package com.api.protecaoanimal.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "temperamentos")
public class TemperamentosModel implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(generator = "uuid2")
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.CHAR)
    private UUID id;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "animais_temperamentos", 
        joinColumns = { @JoinColumn(name = "idTemperamento") }, 
        inverseJoinColumns = { @JoinColumn(name = "idAnimal") }
    )
    @JsonIgnore
    private List<AnimaisModel> animais;

    @Column(nullable = false)
    private String nome;

    @Column
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime registro;

}