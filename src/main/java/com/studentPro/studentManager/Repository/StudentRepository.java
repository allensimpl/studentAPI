package com.studentPro.studentManager.Repository;

import com.studentPro.studentManager.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(value = "SELECT * FROM students WHERE name = :toFindName", nativeQuery = true)
    List<Student> findByName(String toFindName);

    @Query(value = "SELECT * FROM students WHERE name LIKE :chara%",nativeQuery = true)
    List<Student> findByStartingChar(String chara);

//    @Query(value = "SELECT EXISTS(SELECT * FROM students WHERE `student-id` =:rollNo)",nativeQuery = true)
    @Query(
            value = """
            SELECT CASE WHEN EXISTS
            (
                    SELECT * FROM students WHERE `student-id` =:rollNo
            )
            THEN 'TRUE'
            ELSE 'FALSE'
            END
            """
    ,nativeQuery = true)

    boolean containsID(int rollNo);
//    @Query(value = """
//            SELECT CASE WHEN EXISTS
//            (
//                    SELECT * FROM students WHERE `student-id` =:rollNo AND subject = :subject
//            )
//            THEN 'TRUE'
//            ELSE 'FALSE'
//            END
//            """
//    ,nativeQuery = true)
//    boolean isNEW(int rollNo, String subject);
}
