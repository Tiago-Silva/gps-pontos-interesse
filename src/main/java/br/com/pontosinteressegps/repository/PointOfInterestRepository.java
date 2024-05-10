package br.com.pontosinteressegps.repository;

import br.com.pontosinteressegps.entity.PointOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Integer> {

    @Query("" +
            "SELECT p FROM PointOfInterest p " +
            "WHERE p.x >= :xMin AND p.x <= :xMax AND p.y >= :yMin AND p.y <= :yMax" +
        "")
    List<PointOfInterest> findAllByXBetweenAndYBetween(
            @Param("xMin") int xMin,
            @Param("xMax") int xMax,
            @Param("yMin") int yMin,
            @Param("yMax") int yMax);
}
