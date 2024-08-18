package spring_boot_proyek.tes_magang_api.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_boot_proyek.tes_magang_api.dto.ApiResponse;
import spring_boot_proyek.tes_magang_api.dto.ProyekRequest;
import spring_boot_proyek.tes_magang_api.entity.Proyek;
import spring_boot_proyek.tes_magang_api.service.ProyekService;

@RestController
@RequestMapping("/api/proyek")
@CrossOrigin(origins = "http://localhost")
public class ProyekController {

    @Autowired
    private ProyekService proyekService;

    @GetMapping("") // GET /api/proyek
    public ApiResponse<Page<Proyek>> findAllProyek(
            @RequestParam(value = "page", defaultValue = "0") int page) {

        Pageable pageable = PageRequest.of(page, 20);
        Page<Proyek> proyekPage = proyekService.getProyek(pageable);

        ApiResponse.Pagination pagination = new ApiResponse.Pagination(
                proyekPage.getNumber(),
                proyekPage.getTotalElements(),
                proyekPage.getTotalPages()
        );

        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Proyek retrieved successfully",
                proyekPage,
                pagination
        );
    }

    @GetMapping("/{id}")  // GET /api/proyek/{id}
    public ApiResponse<Proyek> findByProyekId(@PathVariable Long id) {
        Proyek proyek = proyekService.getProyekById(id);
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Proyek retrieved successfully",
                proyek
        );
    }

    @PostMapping("")  // POST /api/proyek
    public ApiResponse<Proyek> createProyek(@Valid @RequestBody ProyekRequest request) {
        Proyek savedProyek = proyekService.createProyek(request);
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "Proyek has been created successfully",
                savedProyek
        );
    }

    @PutMapping("/{id}")  // PUT /api/proyek/{id}
    public ApiResponse<Proyek> updateProyek(@PathVariable Long id, @Valid @RequestBody ProyekRequest request) {
        Proyek updatedProyek = proyekService.updateProyek(id, request);
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                "Proyek updated successfully",
                updatedProyek
        );
    }

    @DeleteMapping("/{id}")  // DELETE /api/proyek
    public ApiResponse<String> deleteProyek(@PathVariable Long id) {
        String message = proyekService.deleteProyek(id);
        return new ApiResponse<>(
                HttpStatus.OK.value(),
                message,
                null
        );
    }
}
