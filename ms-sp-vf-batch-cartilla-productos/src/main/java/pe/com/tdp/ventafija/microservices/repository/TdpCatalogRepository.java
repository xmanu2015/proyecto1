package pe.com.tdp.ventafija.microservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.com.tdp.ventafija.microservices.domain.TdpCatalogEntity;

@Repository
public interface TdpCatalogRepository extends JpaRepository<TdpCatalogEntity,Integer> {
}
