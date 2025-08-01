package com.convocatoria.api_docente.service;

import com.convocatoria.api_docente.model.Docente;
import com.convocatoria.api_docente.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    public Docente crearDocente(Docente docente) {
        Optional<Docente> existente = docenteRepository.findByNumeroDocumentoAndTipoDocumento(
                docente.getNumeroDocumento(), docente.getTipoDocumento());
        if (existente.isPresent()) {
            throw new IllegalArgumentException("Ya existe un docente con ese tipo y número de documento");
        }
        return docenteRepository.save(docente);
    }

    public List<Docente> obtenerTodosDocentes() {
        return docenteRepository.findAll();
    }

    public Optional<Docente> obtenerDocentePorId(Long id) {
        return docenteRepository.findById(id);
    }

    public Docente actualizarDocente(Long id, Docente docente) {
        if (docenteRepository.existsById(id)) {
            docente.setId(id);
            return docenteRepository.save(docente);
        }
        return null;
    }

    public void eliminarDocente(Long id) {
        if (!docenteRepository.existsById(id)) {
            throw new IllegalArgumentException("El docente con id " + id + " no existe");
        }
        docenteRepository.deleteById(id);
    }
}