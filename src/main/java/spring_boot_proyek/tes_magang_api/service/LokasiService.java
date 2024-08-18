package spring_boot_proyek.tes_magang_api.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import spring_boot_proyek.tes_magang_api.entity.Lokasi;
import spring_boot_proyek.tes_magang_api.entity.Proyek;
import spring_boot_proyek.tes_magang_api.exception.ResourceNotFoundException;
import spring_boot_proyek.tes_magang_api.repository.LokasiRepository;

@Service
public class LokasiService {

    @Autowired
    private LokasiRepository lokasiRepository;

    // Get All Lokasi dengan Pagination tanpa proyekLokasiSet
    public Page<Lokasi> getLokasi(Pageable pageable) {
        return lokasiRepository.findAllWithoutProyekLokasi(pageable);
    }

    // Get Lokasi berdasarkan Id
    public Lokasi getLokasiById(Long id) {
        return lokasiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lokasi not found with id: " + id));
    }

    // Create New Lokasi
    public Lokasi saveLokasi(Lokasi lokasi) {
        return lokasiRepository.save(lokasi);
    }

    // Edit Lokasi berdasarkan id
    public Lokasi updateLokasi(Long id, Lokasi lokasi) {
        Lokasi existingLokasi = lokasiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lokasi not found with id: " + id));

        existingLokasi.setNamaLokasi(lokasi.getNamaLokasi());
        existingLokasi.setNegara(lokasi.getNegara());
        existingLokasi.setProvinsi(lokasi.getProvinsi());
        existingLokasi.setKota(lokasi.getKota());

        return lokasiRepository.save(existingLokasi);
    }

    // Delete Lokasi berdasarkan id
    public String deleteLokasi(Long id) {
        Lokasi lokasi = lokasiRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyek not found with id: " + id));
        lokasiRepository.delete(lokasi);
        return "Proyek deleted successfully";
    }

//    public List<Lokasi> saveLokasi(List<Lokasi> lokasis) {
//        return lokasiRepository.saveAll(lokasis);
//    }
}
