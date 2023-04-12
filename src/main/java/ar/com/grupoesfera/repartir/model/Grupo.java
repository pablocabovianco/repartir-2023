package ar.com.grupoesfera.repartir.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String nombre;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "miembro")
    private List<String> miembros;

    private BigDecimal total;

    public Grupo() {
    }

    public Grupo(long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getMiembros() {
        return miembros;
    }

    public void setMiembros(List<String> miembros) {
        this.miembros = miembros;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean estaFormado() {

        return (miembros != null) && (miembros.size() > 1) && (!tieneMiembrosDuplicados());
    }

    private boolean tieneMiembrosDuplicados() {
        Set<String> miembrosUnicos = new HashSet<>();
        for (String element : miembros) {
            if (!miembrosUnicos.add(element)) {
                return true;
            }
        }
        return false;
    }
}
