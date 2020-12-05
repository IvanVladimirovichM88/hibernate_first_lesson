//package ru.geekbrains.lesson4.repositories;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import ru.geekbrains.lesson4.data.CartData;
//import ru.geekbrains.lesson4.entity.Cart;
//
////@Repository
//public interface CartDataRepository extends JpaRepository<Cart, Long> {
//    @Query("SELECT new ru.geekbrains.lesson4.data.CartData(c) FROM Cart c JOIN c.user u WHERE c.code = :code")
//    CartData findByCode(@Param("code") String code);
//}
