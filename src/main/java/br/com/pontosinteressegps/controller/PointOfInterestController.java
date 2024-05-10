package br.com.pontosinteressegps.controller;

import br.com.pontosinteressegps.DTO.PointOfInterestRequestDTO;
import br.com.pontosinteressegps.entity.PointOfInterest;
import br.com.pontosinteressegps.repository.PointOfInterestRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/gps")
public class PointOfInterestController {

    private final PointOfInterestRepository repository;
    public PointOfInterestController(PointOfInterestRepository pointOfInterestRepository) {
        this.repository = pointOfInterestRepository;
    }

    @PostMapping("/point-of-interest")
    public ResponseEntity<HttpStatus> createPointOfInterest(@RequestBody PointOfInterestRequestDTO requestDTO) {
        this.repository.save(new PointOfInterest(requestDTO));
        return new ResponseEntity<>(HttpStatus.CREATED, HttpStatus.OK);
    }

    @GetMapping("/point-of-interest")
    public ResponseEntity<Page<PointOfInterest>> listPoi(
            @RequestHeader(name = "page", defaultValue = "0") Integer page,
            @RequestHeader(name = "pageSize", defaultValue = "10") Integer pageSize
    ) {
        return ResponseEntity.ok(this.repository.findAll(PageRequest.of(page, pageSize)));
    }

    @GetMapping("/near-me")
    public ResponseEntity<List<PointOfInterest>> nearMe(
            @RequestHeader(name = "x", defaultValue = "0") Integer x,
            @RequestHeader(name = "y", defaultValue = "0") Integer y,
            @RequestHeader(name = "distance", defaultValue = "0") Integer distance
    ) {

        return ResponseEntity.ok(
            this.repository.findAllByXBetweenAndYBetween(
            x - distance, x + distance, y - distance, y + distance
            )
                .stream()
                .filter(poi -> distanceBetweenPoints(x, y, poi.getX(), poi.getY()) <= distance)
                .toList()
        );
    }

    private Double distanceBetweenPoints(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
