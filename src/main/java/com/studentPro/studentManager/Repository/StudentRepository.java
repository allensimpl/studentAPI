package com.studentPro.studentManager.Repository;

import com.studentPro.studentManager.Entity.Student;
import com.studentPro.studentManager.view.IStudentMarkView;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Query(value = "SELECT * FROM students WHERE name=:toFindName", nativeQuery = true)
    List<Student> findByName(String toFindName);

    @Query(value = "SELECT * FROM students WHERE name LIKE :chara%", nativeQuery = true)
    List<Student> findByStartingChar(String chara);

//    @Query(value = """
//                    SELECT CASE WHEN EXISTS
//                    (
//                            SELECT * FROM students WHERE id =:rollNo
//                    )
//                    THEN 'TRUE'
//                    ELSE 'FALSE'
//                    END
//                    """, nativeQuery = true)
//    boolean containsID(int rollNo);
    @Query(value = "SELECT COUNT(*) FROM students WHERE id = :rollNO",nativeQuery = true)
    int containsID(@Param("rollNO") int rollNo);

    @Query(value = "SELECT COUNT(*) FROM students WHERE id IN :ids",nativeQuery = true)
    int containsIDList(List<Integer> ids);

    @Query(value = "SELECT * FROM students where (:search is null or name like concat('%',:search,'%'))",nativeQuery = true)
    Page<Student> getAllStudents(@Param("search") String search, Pageable pagingParams);

    @Query(value="SELECT * FROM students where email = :email",nativeQuery = true)
    Student getByEmail(String email);

    @Query(value ="SELECT COUNT(*) from students where email = :email",nativeQuery = true)
    int emailCount(String email);

    @Query(value = "SELECT COUNT(*) from students where email in :emails",nativeQuery = true)
    int containsEmailIDs(List<String> emails);

    @Query(value = """
           SELECT
           S.name AS studentName,
           Sub.subject AS subject,
           M.mark AS mark
           FROM marks M
           JOIN students S ON M.st_id = S.id
           JOIN subjects Sub ON M.subject_id = Sub.id
           ORDER BY
           M.mark DESC;
        """, nativeQuery = true)
    List<IStudentMarkView> getResults();
}