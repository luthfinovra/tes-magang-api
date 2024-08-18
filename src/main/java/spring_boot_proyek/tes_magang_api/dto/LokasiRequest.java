package spring_boot_proyek.tes_magang_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LokasiRequest {
    @NotBlank()
    @Size(min = 1, max = 255)
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
}
