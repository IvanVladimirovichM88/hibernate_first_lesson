package ru.geekbrains.lesson4.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.lesson4.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
