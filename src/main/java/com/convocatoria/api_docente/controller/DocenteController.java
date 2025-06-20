package com.convocatoria.api_docente.controller;

import com.convocatoria.api_docente.model.Docente;
import com.convocatoria.api_docente.service.DocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docentes")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @PostMapping
    public ResponseEntity<Docente> crearDocente(@RequestBody Docente docente) {
        return new ResponseEntity<>(docenteService.crearDocente(docente), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Docente>> obtenerTodosDocentes() {
        return new ResponseEntity<>(docenteService.obtenerTodosDocentes(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Docente> actualizarDocente(@PathVariable("id") Long id, @RequestBody Docente docente) {
        Docente docenteActualizado = docenteService.actualizarDocente(id, docente);
        if (docenteActualizado != null) {
            return new ResponseEntity<>(docenteActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDocente(@PathVariable("id") Long id) {
        docenteService.eliminarDocente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}