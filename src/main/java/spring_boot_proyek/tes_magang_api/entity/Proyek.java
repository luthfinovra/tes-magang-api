package spring_boot_proyek.tes_magang_api.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "proyek")
public class Proyek {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "nama_proyek")
    private String namaProyek;

    @NotBlank
    @Size(min = 1, max = 255)
    private String client;

    @NotNull
    @Column(name = "tgl_mulai")
    private LocalDateTime tglMulai;

    @NotNull
    @Column(name = "tgl_selesai")
    private LocalDateTime tglSelesai;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "pimpinan_proyek")
    private String pimpinanProyek;

    @NotBlank
    @Size(min = 1, max = 255)
    private String keterangan;

    @OneToMany(mappedBy = "proyek", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<ProyekLokasi> proyekLokasiSet = new HashSet<>();
}

