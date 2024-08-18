package spring_boot_proyek.tes_magang_api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ProyekLokasiKey implements Serializable {

    @Column(name = "proyek_id")
    private Long proyekId;

    @Column(name = "lokasi_id")
    private Long lokasiId;
}
