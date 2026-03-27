package frontend.sgap.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "archivos")
@Data
public class Archivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    @Column(name = "tipo_archivo")
    private String tipoArchivo;

    private String hash;

    private Long tamano;

    private String ruta;

    @Column(name = "nivel_riesgo")
    private String nivelRiesgo;

    private String estado;

    @ManyToOne
    @JoinColumn(name = "amenaza_id")
    private Amenaza amenaza;
}