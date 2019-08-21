package com.inna.contactmgr.model;

import javax.persistence.*;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    @Column
    private String email;

    @Column
    private Long phone;

    public Contact() {

    }

    public Contact(ContactBuilder builder){
        this.firstname = builder.firstname;
        this.lastname = builder.lastname;
        this.email = builder.email;
        this.phone = builder.phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public static class ContactBuilder{

        private String firstname;
        private String lastname;
        private String email;
        private Long phone;

        public ContactBuilder(String firstname, String lastname) {
            this.firstname = firstname;
            this.lastname = lastname;
        }

        public ContactBuilder withEmail(String email) {
            this.email = email;
            return this;
        }

        public ContactBuilder withPhone(Long phone) {
            this.phone = phone;
            return this;
        }

        public Contact build(){
            return new Contact(this);
        }

    }
}
