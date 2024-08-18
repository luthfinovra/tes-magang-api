package spring_boot_proyek.tes_magang_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring_boot_proyek.tes_magang_api.entity.ProyekLokasi;
import spring_boot_proyek.tes_magang_api.entity.ProyekLokasiKey;

import java.util.Set;

public interface ProyekLokasiRepository extends JpaRepository<ProyekLokasi, ProyekLokasiKey> {
    Set<ProyekLokasi> findByProyekId(Long proyekId);
}
