package ru.sozaev.springcourse.dao;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.sozaev.springcourse.models.Person;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void testNPlus1() {
        Session session = entityManager.unwrap(Session.class);

        // 1 request

        List<Person> people = session.createQuery("select p from Person p", Person.class).getResultList();

        // N request to Data
        for (Person person : people) {
            System.out.println("Person " + person.getName() + " has: " + person.getItems());
        }
    }

    @Transactional
    public void testJoin() {
        Session session = entityManager.unwrap(Session.class);
        List<Person> people = session.createQuery("select p from Person p left join fetch p.Items").getResultList();

        for (Person person : people) {
            System.out.println("Person " + person.getName() + " has: " + person.getItems());
        }
    }

}
