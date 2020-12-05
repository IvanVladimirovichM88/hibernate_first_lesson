package ru.geekbrains.lesson4.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.lesson4.entity.CartEntry;

@Repository
public interface CartEntryRepository extends JpaRepository<CartEntry, Long> {
}
