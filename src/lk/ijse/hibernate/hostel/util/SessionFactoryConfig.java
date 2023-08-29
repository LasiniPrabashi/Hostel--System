package lk.ijse.hibernate.hostel.util;


import lk.ijse.hibernate.hostel.entity.Reservation;
import lk.ijse.hibernate.hostel.entity.Room;
import lk.ijse.hibernate.hostel.entity.Student;
import lk.ijse.hibernate.hostel.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.Properties;


public class SessionFactoryConfig {
    private static SessionFactoryConfig factoryConfig;

    private SessionFactoryConfig(){}

    public static SessionFactoryConfig getInstance(){

        return (null == factoryConfig) ? factoryConfig = new SessionFactoryConfig()
                : factoryConfig;
    }

    public Session getSession(){
        //create a service registtry
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().loadProperties("hibernate.properties").build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Student.class)
               // .addAnnotatedClass(Room.class)
                .addAnnotatedClass(Reservation.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        SessionFactory sessionFactory = metadata.buildSessionFactory();
        return sessionFactory.openSession();
    }


}
