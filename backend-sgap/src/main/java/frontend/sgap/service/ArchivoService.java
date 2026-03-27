package frontend.sgap.service;

import frontend.sgap.entity.Archivo;
import frontend.sgap.entity.Evento;
import frontend.sgap.repository.ArchivoRepository;
import frontend.sgap.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.MessageDigest;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArchivoService {

    @Autowired
    private ArchivoRepository repository;

    @Autowired
    private EventoRepository eventoRepository;

    public Archivo guardar(MultipartFile file, String nivelRiesgo) throws Exception {

        Archivo archivo = new Archivo();

        archivo.setNombre(file.getOriginalFilename());
        archivo.setTipoArchivo(file.getContentType());
        archivo.setTamano(file.getSize());
        archivo.setHash(generarHash(file.getBytes()));
        archivo.setNivelRiesgo(nivelRiesgo);
        archivo.setEstado("ANALIZADO");

        Archivo saved = repository.save(archivo);

        registrarEvento("UPLOAD", "Archivo subido: " + saved.getNombre());

        return saved;
    }

    private String generarHash(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(data);

        StringBuilder hex = new StringBuilder();
        for (byte b : hash) {
            hex.append(String.format("%02x", b));
        }
        return hex.toString();
    }

    private void registrarEvento(String tipo, String desc) {
        Evento evento = new Evento();
        evento.setTipoEvento(tipo);
        evento.setDescripcion(desc);
        evento.setFecha(LocalDateTime.now());
        eventoRepository.save(evento);
    }

    public List<Archivo> listar() {
        return repository.findAll();
    }
}