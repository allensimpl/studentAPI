package com.studentPro.studentManager.Repository;

import com.studentPro.studentManager.Entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(value = "SELECT * FROM students WHERE name = :toFindName", nativeQuery = true)
    List<Student> findByName(String toFindName);

    @Query(value = "SELECT * FROM students WHERE name LIKE :chara%", nativeQuery = true)
    List<Student> findByStartingChar(String chara);

    //    @Query(value = "SELECT EXISTS(SELECT * FROM students WHERE `student-id` =:rollNo)",nativeQuery = true)

    @Query(
            value = """
                    SELECT CASE WHEN EXISTS
                    (
                            SELECT * FROM students WHERE id =:rollNo
                    )
                    THEN 'TRUE'
                    ELSE 'FALSE'
                    END
                    """
            , nativeQuery = true)
    boolean containsID(int rollNo);

//    @Query(value = "SELECT * FROM students WHERE COALESCE(name LIKE :search,name LIKE %%)",nativeQuery = true)
    @Query(value = "SELECT * FROM students where (:search is null or name like concat('%',:search,'%'))",nativeQuery = true)
    Page<Student> getAllStudents(@Param("search") String search, Pageable pagingParams);
    @Query(
            value = """
                    SELECT CASE WHEN EXISTS
                    (
                            SELECT * FROM students WHERE email =:email
                    )
                    THEN 'TRUE'
                    ELSE 'FALSE'
                    END
                    """
            , nativeQuery = true)
    boolean containsEmail(String email);

    @Query(value = """
                    SELECT CASE WHEN EXISTS
                    (
                            SELECT * FROM students WHERE email IN :emails
                    )
                    THEN 'TRUE'
                    ELSE 'FALSE'
                    END

            """,nativeQuery = true)
    boolean containsEmailIDs(List<String> emails);
}