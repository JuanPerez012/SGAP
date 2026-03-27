package frontend.sgap.controller;

import frontend.sgap.entity.Archivo;
import frontend.sgap.service.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/archivos")
@CrossOrigin("*")
public class ArchivoController {

    @Autowired
    private ArchivoService service;

    @PostMapping
    public Archivo subir(
            @RequestParam("file") MultipartFile file,
            @RequestParam("nivelRiesgo") String nivelRiesgo
    ) throws Exception {
        return service.guardar(file, nivelRiesgo);
    }

    @GetMapping
    public List<Archivo> listar() {
        return service.listar();
    }
}