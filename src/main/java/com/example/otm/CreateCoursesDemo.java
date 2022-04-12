package com.example.otm;

import com.example.otm.model.Course;
import com.example.otm.model.Instructor;
import com.example.otm.model.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            int id = 1;
            Instructor tempInstructor = session.get(Instructor.class, id);

            Course course1 = new Course("Air Guitar = The Ultimate Guide");
            Course course2 = new Course("The Pinball Masterclass");

            tempInstructor.add(course1);
            tempInstructor.add(course2);

            session.save(course1);
            session.save(course2);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
