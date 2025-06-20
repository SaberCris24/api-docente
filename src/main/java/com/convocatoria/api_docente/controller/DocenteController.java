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
    public ResponseEntity<?> crearDocente(@RequestBody Docente docente) {
        // If the request body contains an id, reject the request
        if (docente.getId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No se puede establecer el id del docente al crear uno nuevo");
        }
        Docente nuevoDocente = docenteService.crearDocente(docente);
        return new ResponseEntity<>(nuevoDocente, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Docente>> obtenerTodosDocentes() {
        return new ResponseEntity<>(docenteService.obtenerTodosDocentes(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarDocente(@PathVariable("id") Long id, @RequestBody Docente docente) {
        // Check if the request body contains an id and if it matches the path variable
        if (docente.getId() != null && !docente.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("No se puede editar el id del docente");
        }
        Docente docenteActualizado = docenteService.actualizarDocente(id, docente);
        if (docenteActualizado != null) {
            return new ResponseEntity<>(docenteActualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarDocente(@PathVariable("id") Long id) {
        try {
            docenteService.eliminarDocente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}