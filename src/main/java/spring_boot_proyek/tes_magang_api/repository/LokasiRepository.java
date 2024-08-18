package spring_boot_proyek.tes_magang_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring_boot_proyek.tes_magang_api.entity.Lokasi;

@Repository
public interface LokasiRepository extends JpaRepository<Lokasi, Long> {

    // Tidak fetch relationship saat fetch lokasi
    @Query("SELECT l FROM Lokasi l")
    Page<Lokasi> findAllWithoutProyekLokasi(Pageable pageable);
}
