package com.convocatoria.api_docente.repository;

import com.convocatoria.api_docente.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
    Optional<Docente> findByNumeroDocumentoAndTipoDocumento(String numeroDocumento, String tipoDocumento);
}