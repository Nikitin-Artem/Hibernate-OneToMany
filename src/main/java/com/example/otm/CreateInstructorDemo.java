package com.example.otm;

import com.example.otm.model.Course;
import com.example.otm.model.Instructor;
import com.example.otm.model.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            Instructor tempInstructor = new Instructor("Eren", "Eager", "eren.eager@gmail.com");
            InstructorDetail tempInstructorDetail = new InstructorDetail("Spring", "Tennis");
            tempInstructor.setInstructorDetail(tempInstructorDetail);

            session.beginTransaction();


            System.out.println("Saving instructor: " + tempInstructor);
            session.save(tempInstructor);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
