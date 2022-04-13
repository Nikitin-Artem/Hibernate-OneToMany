package com.example.otm;

import com.example.otm.model.Course;
import com.example.otm.model.Instructor;
import com.example.otm.model.InstructorDetail;
import com.example.otm.model.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviewsDemo {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            int id = 16;
            Course tempCourse = session.get(Course.class, id);

            System.out.println("Deleting the course ... ");
            System.out.println(tempCourse);
            System.out.println(tempCourse.getReviews());

            session.delete(tempCourse);

            session.getTransaction().commit();

            System.out.println("Done!");
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}
