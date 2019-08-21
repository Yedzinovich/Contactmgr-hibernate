package com.inna.contactmgr;

import com.inna.contactmgr.model.Contact;
import com.inna.contactmgr.model.Contact.ContactBuilder;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;


public class Application {
    //hold a reusable reference to a SessionFactory
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        //create a standardServiceRegistry
        final ServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        return new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    public static void main(String[] args) {
        Contact contact = new ContactBuilder("Inna", "Yed")
                .withEmail("innayed@yahoo.com")
                .withPhone(1234567890L)
                .build();

        int id = save(contact);

        //display list
        fetchAllContacts().stream().forEach(System.out::println);

        //get persisted contact
        Contact c = findContactById(id);

        //update the content
        c.setFirstname("Ben");

        //persist the changes
        update(c);

        //display a list of contacts after the update
        System.out.println();
        fetchAllContacts().stream().forEach(System.out::println);

        delete(c);
        System.out.println();
        fetchAllContacts().stream().forEach(System.out::println);

    }

    private static int save(Contact contact){

        // open a session
        // begin a transaction
        // use the session to save the contact
        // commit the transaction
        // close the session

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        int id = (int)session.save(contact);

        session.getTransaction().commit();

        session.close();

        return id;
    }

    @SuppressWarnings("unchecked")
    private static List<Contact> fetchAllContacts(){
        Session session = sessionFactory.openSession();

        //create a criteria object
        CriteriaQuery<Contact> criteriaQuery = session.getCriteriaBuilder().createQuery(Contact.class);
        criteriaQuery.from(Contact.class);

        List<Contact> contacts = session.createQuery(criteriaQuery).getResultList();

        session.close();

        return contacts;
    }

    private static Contact findContactById(int id){
        Session session = sessionFactory.openSession();

        Contact contact = session.get(Contact.class, id);

        session.close();

        return contact;
    }

    private static void update(Contact contact){
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.update(contact);

        session.getTransaction().commit();

        session.close();
    }

    private static void delete(Contact contact){
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.delete(contact);

        session.getTransaction().commit();

        session.close();
    }
}
