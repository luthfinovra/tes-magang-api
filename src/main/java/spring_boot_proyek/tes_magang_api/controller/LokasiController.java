package spring_boot_proyek.tes_magang_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import spring_boot_proyek.tes_magang_api.dto.ApiResponse;
import spring_boot_proyek.tes_magang_api.dto.LokasiRequest;
import spring_boot_proyek.tes_magang_api.entity.Lokasi;
import spring_boot_proyek.tes_magang_api.service.LokasiService;

@RestController
@RequestMapping("/api/lokasi")
public class LokasiController {

    @Autowired
    private LokasiService lokasiService;

    @GetMapping("") // GET /api/lokasi
    public ApiResponse<Page<Lokasi>> getAllLokasi(
            @RequestParam(value = "page", defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, 10);
        Page<Lokasi> lokasiPage = lokasiService.getLokasi(pageable);

        ApiResponse.Pagination pagination = new ApiResponse.Pagination(
                lokasiPage.getNumber(),
                lokasiPage.getTotalElements(),
                lokasiPage.getTotalPages()
        );

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Lokasi retrieved successfully",
                lokasiPage,
                pagination
        );
    }

    @GetMapping("/{id}") // GET /api/lokasi/{id}
    public ApiResponse<Lokasi> getLokasiById(@PathVariable Long id) {
        Lokasi lokasi = lokasiService.getLokasiById(id);
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Lokasi retrieved successfully",
                lokasi
        );
    }

    @PostMapping("") // POST /api/lokasi
    public ApiResponse<Lokasi> addLokasi(@Valid @RequestBody LokasiRequest lokasiRequest) {
        Lokasi lokasi = new Lokasi();
        lokasi.setNamaLokasi(lokasiRequest.getNamaLokasi());
        lokasi.setNegara(lokasiRequest.getNegara());
        lokasi.setProvinsi(lokasiRequest.getProvinsi());
        lokasi.setKota(lokasiRequest.getKota());

        Lokasi createdLokasi = lokasiService.saveLokasi(lokasi);
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Lokasi has been created successfully",
                createdLokasi
        );
    }

    @PutMapping("/{id}") // PUT /api/lokasi/{id}
    public ApiResponse<Lokasi> updateLokasi(@PathVariable Long id, @Valid @RequestBody LokasiRequest lokasiRequest) {
        Lokasi existingLokasi = lokasiService.getLokasiById(id);
        existingLokasi.setNamaLokasi(lokasiRequest.getNamaLokasi());
        existingLokasi.setNegara(lokasiRequest.getNegara());
        existingLokasi.setProvinsi(lokasiRequest.getProvinsi());
        existingLokasi.setKota(lokasiRequest.getKota());

        Lokasi updatedLokasi = lokasiService.saveLokasi(existingLokasi);
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Lokasi updated successfully",
                updatedLokasi
        );
    }

    @DeleteMapping("/{id}") // DELETE /api/lokasi/{id}
    public ApiResponse<String> deleteLokasi(@PathVariable Long id) {
        String message = lokasiService.deleteLokasi(id);
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                message,
                null
        );
    }
}
