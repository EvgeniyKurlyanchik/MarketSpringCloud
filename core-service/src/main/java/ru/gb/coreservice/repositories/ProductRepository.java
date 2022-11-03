package ru.gb.coreservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.coreservice.entities.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}