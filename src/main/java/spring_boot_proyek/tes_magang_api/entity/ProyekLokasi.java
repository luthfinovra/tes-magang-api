package spring_boot_proyek.tes_magang_api.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proyek_lokasi")
public class ProyekLokasi {

    @EmbeddedId
    private ProyekLokasiKey id;

    @MapsId("proyekId")
    @ManyToOne
    @JoinColumn(name = "proyek_id", nullable = true)
    @JsonBackReference
    private Proyek proyek;

    @MapsId("lokasiId")
    @ManyToOne
    @JoinColumn(name = "lokasi_id", nullable = true)
    private Lokasi lokasi;
}
