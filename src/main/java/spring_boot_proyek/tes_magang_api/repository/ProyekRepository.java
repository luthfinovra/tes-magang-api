package spring_boot_proyek.tes_magang_api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring_boot_proyek.tes_magang_api.entity.Proyek;

import java.util.Optional;

@Repository
public interface ProyekRepository extends JpaRepository<Proyek, Long> {
    // Mengatasi N+1 Query namun mengakibatkan stackoverflow
    // TO DO Resolve N+1 Query problem
//    @Query("SELECT p FROM Proyek p LEFT JOIN FETCH p.proyekLokasiSet pl LEFT JOIN FETCH pl.lokasi WHERE p.id = :id")
//    Optional<Proyek> findByIdWithProyekLokasiSet(@Param("id") Long id);
//
//    @Query("SELECT p FROM Proyek p LEFT JOIN FETCH p.proyekLokasiSet pl LEFT JOIN FETCH pl.lokasi")
//    Page<Proyek> findAllWithProyekLokasiSet(Pageable pageable);

//    @EntityGraph(attributePaths = {"proyekLokasiSet", "proyekLokasiSet.lokasi"})
//    Page<Proyek> findAll(Pageable pageable);
//
//    @EntityGraph(attributePaths = {"proyekLokasiSet", "proyekLokasiSet.lokasi"})
//    Optional<Proyek> findById(Long id);
}
