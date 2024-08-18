package spring_boot_proyek.tes_magang_api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "lokasi")
public class Lokasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank()
    @Size(min = 1, max = 255)
    @Column(name = "nama_lokasi")
    private String namaLokasi;

    @NotBlank()
    @Size(min = 1, max = 255)
    private String negara;

    @NotBlank()
    @Size(min = 1, max = 255)
    private String provinsi;

    @NotBlank()
    @Size(min = 1, max = 255)
    private String kota;

    @OneToMany(mappedBy = "lokasi", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ProyekLokasi> proyekLokasiSet = new HashSet<>();
}
