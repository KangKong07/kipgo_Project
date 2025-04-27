package com.spring.backend;

import com.spring.backend.model.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.util.Date;

@SpringBootApplication
@EntityScan(basePackageClasses = Member.class)
public class BackendApplication // implements CommandLineRunner
{

    @Autowired
    EntityManagerFactory emf;

    public static void main(String[] args) {
        SpringApplication app  = new SpringApplication(BackendApplication.class);
        app.run(args);
//        app.setWebApplicationType(WebApplicationType.NONE);
//        app.run(args);
    }

//    @Override
//    public void run(String... args) throws Exception {
//        EntityManager em  = emf.createEntityManager();
//        EntityTransaction tx = em.getTransaction();
//
//        tx.begin();
//
//        try {
//            Member existing = em.find(Member.class, "rudgns3456");
//            if (existing != null) {
//                em.remove(existing); // 테스트용일 경우 삭제
//            }
//
//            Member member = new Member();
//            member.setMEMBER_ID("rudgns3456");
//            member.setMEMBER_PWD("1234");
//            member.setEMAIL("rudgns1234@gmail.com");
//            member.setNAME("강콩");
//            member.setCHK_ID("admin");
//            member.setDELETE_YN("N");
//            member.setJOIN_DATE(new Date());
//            member.setCHK_DATE(new Date());
//            member.setTEL_NO("010-1234-5678");
//
//            em.persist(member);
//            tx.commit();
//
//        } catch (Exception e) {
//            tx.rollback();
//            e.printStackTrace();
//        } finally {
//            em.close();
//        }
//    }
}
