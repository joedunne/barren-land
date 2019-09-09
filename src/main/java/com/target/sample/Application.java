package com.target.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class Application {

    private final BarrenLandAnalysis barrenLandAnalysis;

    @Autowired
    public Application(BarrenLandAnalysis barrenLandAnalysis) {
        this.barrenLandAnalysis = barrenLandAnalysis;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter barren land coordinates. Sample {\"0 292 399 307\"}");
            String input = null;
            if (scanner.hasNext()) {
                input = scanner.nextLine();
            }
            System.out.println(barrenLandAnalysis.calculateFertileLandAreas(input));
        };
    }

}
