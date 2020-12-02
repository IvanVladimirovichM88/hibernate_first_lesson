package ru.geekbrains.hibrnate.lesson3.entity;


import javax.persistence.*;

@Entity
@Table(name = "person_details_tbl")
public class PersonDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_details_id")
    private Long id;

    @Column(name = "description_fld")
    private String description;

    @Column(name = "phone_fld")
    private String phone;

    @OneToOne(mappedBy = "personDetails")
    private Person person;

    public PersonDetails() {
    }

    public PersonDetails(String description, String phone) {
        this.description = description;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "PersonDetails{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", phone='" + phone + '\'' +
                ", person=" + person +
                '}';
    }
}
