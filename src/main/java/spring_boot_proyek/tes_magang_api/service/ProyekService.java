    package spring_boot_proyek.tes_magang_api.service;

    import jakarta.transaction.Transactional;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Service;
    import spring_boot_proyek.tes_magang_api.dto.ProyekRequest;
    import spring_boot_proyek.tes_magang_api.entity.Lokasi;
    import spring_boot_proyek.tes_magang_api.entity.Proyek;
    import spring_boot_proyek.tes_magang_api.entity.ProyekLokasi;
    import spring_boot_proyek.tes_magang_api.entity.ProyekLokasiKey;
    import spring_boot_proyek.tes_magang_api.exception.ResourceNotFoundException;
    import spring_boot_proyek.tes_magang_api.repository.LokasiRepository;
    import spring_boot_proyek.tes_magang_api.repository.ProyekLokasiRepository;
    import spring_boot_proyek.tes_magang_api.repository.ProyekRepository;

    import java.beans.Transient;
    import java.util.HashSet;
    import java.util.Set;
    import java.util.stream.Collectors;

    @Service
    public class ProyekService {

        @Autowired
        private ProyekRepository proyekRepository;

        @Autowired
        private LokasiRepository lokasiRepository;

        @Autowired
        private ProyekLokasiRepository proyekLokasiRepository;

        // N+1 Query Problem masih belum teratasi
        public Page<Proyek> getProyek(Pageable pageable) {
            Page<Proyek> proyekPage = proyekRepository.findAll(pageable);
            proyekPage.forEach(proyek -> proyek.setProyekLokasiSet(proyekLokasiRepository.findByProyekId(proyek.getId())));
            return proyekPage;
        }

        // N+1 Query Problem masih belum teratasi
        public Proyek getProyekById(Long id) {
            Proyek proyek = proyekRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Proyek not found with id: " + id));
            proyek.setProyekLokasiSet(proyekLokasiRepository.findByProyekId(id));
            return proyek;
        }

        // N+1 Query Problem masih belum teratasi
        //@Transactional
        public Proyek createProyek(ProyekRequest request) {
            Proyek proyek = new Proyek();
            proyek.setNamaProyek(request.getNamaProyek());
            proyek.setClient(request.getClient());
            proyek.setTglMulai(request.getTglMulai());
            proyek.setTglSelesai(request.getTglSelesai());
            proyek.setPimpinanProyek(request.getPimpinanProyek());
            proyek.setKeterangan(request.getKeterangan());

            Proyek savedProyek = proyekRepository.save(proyek);

            // Asosiasikan proyek dengan lokasi yang telah dibuat
            Set<ProyekLokasi> proyekLokasiSet = new HashSet<>();
            for (Long lokasiId : request.getLokasiIds()) {
                Lokasi lokasi = lokasiRepository.findById(lokasiId)
                        .orElseThrow(() -> new ResourceNotFoundException("Lokasi not found with id: " + lokasiId));

                ProyekLokasiKey proyekLokasiKey = new ProyekLokasiKey(savedProyek.getId(), lokasi.getId());
                ProyekLokasi proyekLokasi = new ProyekLokasi();
                proyekLokasi.setId(proyekLokasiKey);
                proyekLokasi.setProyek(savedProyek);
                proyekLokasi.setLokasi(lokasi);

                proyekLokasiSet.add(proyekLokasi);
            }

            proyekLokasiRepository.saveAll(proyekLokasiSet);
            savedProyek.setProyekLokasiSet(proyekLokasiSet);

            return savedProyek;
        }

        // N+1 Query Problem masih belum teratasi
        //@Transactional
        public Proyek updateProyek(Long id, ProyekRequest request) {
            Proyek existingProyek = proyekRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Proyek not found with id: " + id));

            existingProyek.setNamaProyek(request.getNamaProyek());
            existingProyek.setClient(request.getClient());
            existingProyek.setTglMulai(request.getTglMulai());
            existingProyek.setTglSelesai(request.getTglSelesai());
            existingProyek.setPimpinanProyek(request.getPimpinanProyek());
            existingProyek.setKeterangan(request.getKeterangan());

            // Update ProyekLokasi
            Set<Long> newLokasiIds = new HashSet<>(request.getLokasiIds());
            Set<Long> currentLokasiIds = new HashSet<>(proyekLokasiRepository.findByProyekId(id).stream()
                    .map(pl -> pl.getLokasi().getId())
                    .collect(Collectors.toSet()));

            // Menentukan lokasi yang akan dihapus pada data proyek
            Set<Long> toDeleteLokasiIds = new HashSet<>(currentLokasiIds);
            toDeleteLokasiIds.removeAll(newLokasiIds);

            // Menentukan lokasi yang akan ditambahkan pada data proyek
            Set<Long> toAddLokasiIds = new HashSet<>(newLokasiIds);
            toAddLokasiIds.removeAll(currentLokasiIds);

            // Delete lokasi yang tidak ada lagi pada data baru
            for (Long lokasiId : toDeleteLokasiIds) {
                ProyekLokasiKey key = new ProyekLokasiKey(id, lokasiId);
                proyekLokasiRepository.deleteById(key);
            }

            // Mendapatkan data lokasi yang tersisa terasosiasi dengan proyek
            Set<ProyekLokasi> existingProyekLokasiSet = new HashSet<>(proyekLokasiRepository.findByProyekId(id));

            // Asosiasikan lokasi baru dengan proyek
            Set<ProyekLokasi> proyekLokasiSet = new HashSet<>(existingProyekLokasiSet);
            for (Long lokasiId : toAddLokasiIds) {
                Lokasi lokasi = lokasiRepository.findById(lokasiId)
                        .orElseThrow(() -> new ResourceNotFoundException("Lokasi not found with id: " + lokasiId));

                ProyekLokasiKey proyekLokasiKey = new ProyekLokasiKey(id, lokasi.getId());
                ProyekLokasi proyekLokasi = new ProyekLokasi();
                proyekLokasi.setId(proyekLokasiKey);
                proyekLokasi.setProyek(existingProyek);
                proyekLokasi.setLokasi(lokasi);

                proyekLokasiSet.add(proyekLokasi);
            }

            proyekLokasiRepository.saveAll(proyekLokasiSet);

            existingProyek.setProyekLokasiSet(proyekLokasiSet);

            return existingProyek;
        }
        
        public String deleteProyek(Long id) {
            Proyek proyek = proyekRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Proyek not found with id: " + id));
            proyekRepository.delete(proyek);
            return "Proyek deleted successfully";
        }
    }
