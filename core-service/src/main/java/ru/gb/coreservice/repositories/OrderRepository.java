package ru.gb.coreservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.coreservice.entities.Order;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
//    @Query("select o from Order o where o.username = ?1")
    List<Order> findAllByUsername(String username);
}
