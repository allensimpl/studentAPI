package com.studentPro.studentManager.Repository;

import com.studentPro.studentManager.Entity.Mark;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarkRepository extends JpaRepository<Mark,Integer> {
    @Query(value = "SELECT * from marks WHERE id=:id",nativeQuery = true)
    List<Mark> findAllOfId(int id);
    @Query(value = "SELECT * from marks WHERE subject=:subject",nativeQuery = true)
    List<Mark> findBySubject(String subject);

    @Query(value="DELETE FROM marks WHERE id=:id AND subject = :subject", nativeQuery = true)
    void deleteMark(int id, String subject);
    @Query(value = """
            SELECT CASE WHEN EXISTS
            (
                    SELECT * FROM marks WHERE `id` =:rollNo AND subject = :subject
            )
            THEN 'TRUE'
            ELSE 'FALSE'
            END
            """
    ,nativeQuery = true)
    boolean isNEW(int rollNo, String subject);

//    @Query(value = "SELECT * FROM students where (:search is null or name like concat('%',:search,'%'))")
    @Query(value = "SELECT * FROM marks WHERE (:subject is null or subject like concat(:subject,'%'))",nativeQuery = true)
    Page<Mark> getAllMarks(@Param("subject") String subject, Pageable pageParams);
}
