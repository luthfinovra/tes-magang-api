package spring_boot_proyek.tes_magang_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProyekRequest {

    @NotBlank
    @Size(min = 1, max = 255)
    private String namaProyek;

    @NotBlank
    @Size(min = 1, max = 255)
    private String client;

    @NotNull
    private LocalDateTime tglMulai;

    @NotNull
    private LocalDateTime tglSelesai;

    @NotBlank
    @Size(min = 1, max = 255)
    private String pimpinanProyek;

    @NotBlank
    @Size(min = 1, max = 255)
    private String keterangan;

    private List<Long> lokasiIds;
}
