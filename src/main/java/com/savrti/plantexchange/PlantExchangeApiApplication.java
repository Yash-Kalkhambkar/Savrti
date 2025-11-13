package com.savrti.plantexchange;

import com.savrti.plantexchange.entity.Plant;
import com.savrti.plantexchange.entity.Request;
import com.savrti.plantexchange.entity.User;

import com.savrti.plantexchange.entity.UserType;
import com.savrti.plantexchange.entity.Condition;
import com.savrti.plantexchange.entity.AvailabilityStatus;
import com.savrti.plantexchange.entity.RequestStatus;

import com.savrti.plantexchange.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlantExchangeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlantExchangeApiApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(UserRepository userRepo, PlantRepository plantRepo, RequestRepository requestRepo) {
        return args -> {
            // Create sample users
            User admin = new User("Admin User", "admin@savrti.com", "admin123", UserType.ADMIN);
            User member1 = new User("John Doe", "john@email.com", "password123", UserType.MEMBER);
            User member2 = new User("Jane Smith", "jane@email.com", "password123", UserType.MEMBER);
            
            userRepo.save(admin);
            userRepo.save(member1);
            userRepo.save(member2);
            
            // Create sample plants
            Plant plant1 = new Plant("Rose Bush", "Rose", "Beautiful red roses", Condition.GOOD, AvailabilityStatus.AVAILABLE, member1);
            Plant plant2 = new Plant("Aloe Vera", "Succulent", "Medicinal aloe plant", Condition.FAIR, AvailabilityStatus.AVAILABLE, member1);
            Plant plant3 = new Plant("Snake Plant", "Indoor", "Low maintenance indoor plant", Condition.GOOD, AvailabilityStatus.UNAVAILABLE, member2);
            
            plantRepo.save(plant1);
            plantRepo.save(plant2);
            plantRepo.save(plant3);
            
            // Create sample requests
            Request request1 = new Request(plant1, member2, RequestStatus.PENDING);
            Request request2 = new Request(plant2, member2, RequestStatus.APPROVED);
            
            requestRepo.save(request1);
            requestRepo.save(request2);
            
            System.out.println("✅ Sample data loaded successfully!");
            System.out.println("🌐 API running at: http://localhost:8080");
            System.out.println("🗄️  H2 Console: http://localhost:8080/h2-console");
        };
    }
}