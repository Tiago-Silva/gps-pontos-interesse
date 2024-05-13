package br.com.pontosinteressegps;

import br.com.pontosinteressegps.entity.PointOfInterest;
import br.com.pontosinteressegps.repository.PointOfInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PontosInteresseGpsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(PontosInteresseGpsApplication.class, args);
    }

    @Autowired
    private PointOfInterestRepository repository;

    @Override
    public void run(String... args) {
        this.repository.save(new PointOfInterest("Lanchonete", 27, 12));
        this.repository.save(new PointOfInterest("Posto", 31, 18));
        this.repository.save(new PointOfInterest("Joalheria", 15, 12));
        this.repository.save(new PointOfInterest("Floricultura", 19, 21));
        this.repository.save(new PointOfInterest("Pub", 12, 8));
        this.repository.save(new PointOfInterest("Supermercado", 23, 6));
        this.repository.save(new PointOfInterest("Churrascaria", 28, 2));
    }
}
