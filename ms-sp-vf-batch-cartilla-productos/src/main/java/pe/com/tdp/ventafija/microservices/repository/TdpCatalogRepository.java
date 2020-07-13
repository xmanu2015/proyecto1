package pe.com.tdp.ventafija.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.tdp.ventafija.microservices.domain.TdpCatalogEntity;

@Repository
public interface TdpCatalogRepository extends JpaRepository<TdpCatalogEntity,Integer> {
//    @Modifying
//    @Query("delete from Book b where b.title=:title")
//    void deleteBooks(@Param("title") String title);

//    @Modifying
//    @Query("delete from tdp_catalog_2 c where c.herramienta = ?1")
//    void deleteUsersByFirstName(String herramienta);
}
