package ru.geekbrains.hibrnate.lesson3.entity_manager;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.hibrnate.lesson3.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;


public class MainApp {

    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Person.class)
                .addAnnotatedClass(PersonDetails.class)
                .addAnnotatedClass(Product.class)
                .addAnnotatedClass(Category.class)
                .addAnnotatedClass(Cart.class)
                .buildSessionFactory();

        EntityManager em = factory.createEntityManager();


        try {
            clearDB(em);
            fillProduct(em);
            fillPerson(em);
            personsAddProductsInCart(em);

            Product product = em.createQuery("SELECT p FROM Product p WHERE p.name = 'three product'", Product.class).getSingleResult() ;
            whatPersonsBuyThisProduct(em,product).stream().forEach(x-> System.out.println(x.getFirstname()));

            Person person =em.createQuery("SELECT p FROM Person p WHERE p.firstname = 'Lilo'", Person.class).getSingleResult();
            whatProductsBuyThisPerson(em,person).stream().forEach(x-> System.out.println(x.getName()));

            Product productDelete = em.createQuery("SELECT p FROM Product p WHERE p.name = 'five product'", Product.class).getSingleResult();
            deleteProduct(em,productDelete);

            Person personDelete =em.createQuery("SELECT p FROM Person p WHERE p.firstname = 'Delete'", Person.class).getSingleResult();
            deletePerson(em,personDelete);


        } finally {
            factory.close();
            if (em != null) {
                em.close();
            }
        }
    }

    private static void clearDB(EntityManager entityManager) {
        entityManager.getTransaction().begin();

        entityManager.createNativeQuery("delete from carts_tbl").executeUpdate();
        entityManager.createNativeQuery("delete from categories_tbl").executeUpdate();
        entityManager.createNativeQuery("delete from person_details_tbl").executeUpdate();
        entityManager.createNativeQuery("delete from persons_tbl" ).executeUpdate();
        entityManager.createNativeQuery("delete from products_carts_tbl" ).executeUpdate();
        entityManager.createNativeQuery("delete from products_tbl" ).executeUpdate();
        entityManager.createNativeQuery("delete from simple_items_tbl").executeUpdate();

        entityManager.getTransaction().commit();
    }

    private static void fillProduct(EntityManager entityManager) {
        //add product with category
        entityManager.getTransaction().begin();

        Category category = new Category();
        category.setTitle("first category");

        Product product1 = new Product("one product", 100.2f, category);
        Product product2 = new Product("two product", 200.2f, category);
        Product product3 = new Product("three product", 300.2f, category);
        Product product4 = new Product("four product", 400.2f, category);
        Product product5 = new Product("five product", 400.2f, category);

        List<Product> productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);

        category.setProducts(productList);
        entityManager.persist(category);

        entityManager.getTransaction().commit();
    }

    private static void fillPerson(EntityManager entityManager) {
        // add person with details and cart
        entityManager.getTransaction().begin();

        PersonDetails details1 = new PersonDetails("description .... 1", "89291841048");
        PersonDetails details2 = new PersonDetails("description .... 2", "89291841050");
        PersonDetails detailsDelete = new PersonDetails("description .... 3", "89261766359");

        Cart cart1 = new Cart();
        Cart cart2 = new Cart();
        Cart cartDelete = new Cart();

        Person person1 = new Person("Lilo", "lilo@test.com", details1);
        Person person2 = new Person("Stich", "stich@test.com", details2);
        Person personDelete = new Person("Delete", "Delete@test.com", detailsDelete);

        person1.setPersonCart(cart1);
        person2.setPersonCart(cart2);
        personDelete.setPersonCart(cartDelete);

        cart1.setPerson(person1);
        cart2.setPerson(person2);
        cartDelete.setPerson((personDelete));

        entityManager.persist(person1);
        entityManager.persist(person2);
        entityManager.persist(personDelete);

        entityManager.getTransaction().commit();
    }

    private static void personsAddProductsInCart(EntityManager entityManager) {
// first person buy 2 products
        entityManager.getTransaction().begin();

        Person firstPerson = entityManager.createQuery(
                "SELECT p FROM Person p WHERE p.firstname = 'Stich'",Person.class
        ).getSingleResult();

        List<Product> selectProducts = entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.name IN ('one product', 'two product')", Product.class
        ).getResultList();

        selectProducts.stream().forEach(x->firstPerson.getPersonCart().getProducts().add(x));

        entityManager.getTransaction().commit();
//second person buy 2 products
        entityManager.getTransaction().begin();

        Person secondPerson = entityManager.createQuery(
                "SELECT p FROM Person p WHERE p.firstname = 'Lilo'",Person.class
        ).getSingleResult();

        selectProducts = entityManager.createQuery(
                "SELECT p FROM Product p WHERE p.name IN ('three product', 'two product')", Product.class
        ).getResultList();

        selectProducts.stream().forEach(x->secondPerson.getPersonCart().getProducts().add(x));

        entityManager.getTransaction().commit();

    }

    private static List<Person> whatPersonsBuyThisProduct(EntityManager entityManager, Product product){

        List<Person> persons = entityManager.createNamedQuery("allPersonsBuyProduct", Person.class)
                .setParameter("ProductId", product.getId())
                .getResultList();

        return persons;
    }

    private static List<Product> whatProductsBuyThisPerson(EntityManager entityManager, Person person){
        List<Product> products = entityManager.createNamedQuery("allProductsInPersonCart", Product.class)
                .setParameter("PersonId", person.getId())
                .getResultList();

        return products;
    }

    private static void deleteProduct(EntityManager entityManager, Product product){
        entityManager.getTransaction().begin();
        entityManager.remove(product);
        entityManager.getTransaction().commit();
    }

    private static void deletePerson(EntityManager entityManager, Person person){
        entityManager.getTransaction().begin();
        entityManager.remove(person);
        entityManager.getTransaction().commit();
    }
}
