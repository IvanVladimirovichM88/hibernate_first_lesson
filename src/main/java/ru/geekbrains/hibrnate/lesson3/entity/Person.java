package ru.geekbrains.hibrnate.lesson3.entity;

import javax.persistence.*;

@Entity
@Table (name = "persons_tbl")
@NamedQuery(name = "deletePerson", query = "DELETE FROM Person p WHERE p.id = :PersonId")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Long id;

    @Column(name = "firstname_fld")
    private String firstname;

    @Column(name = "email_fld")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "person_details_id")
    private PersonDetails personDetails;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    private Cart personCart;

//////////////////////////////////////////////////////////////////////

    public Person() {
    }

    public Person(String firstname, String email, PersonDetails personDetails) {
        this.firstname = firstname;
        this.email = email;
        this.personDetails = personDetails;
    }

    public void delete(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PersonDetails getPersonDetails() {
        return personDetails;
    }

    public void setPersonDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
    }

    public Cart getPersonCart() {
        return personCart;
    }

    public void setPersonCart(Cart personCart) {
        this.personCart = personCart;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
