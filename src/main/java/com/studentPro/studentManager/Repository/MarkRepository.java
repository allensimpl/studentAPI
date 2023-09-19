package com.studentPro.studentManager.Repository;

import com.studentPro.studentManager.Entity.Mark;
import jakarta.persistence.criteria.CriteriaBuilder;
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
    @Query(value = "SELECT * from marks WHERE subject_id=:subjectID",nativeQuery = true)
    List<Mark> findBySubject(int subjectID);

    @Query(value="DELETE FROM marks WHERE id=:id AND subject_id = :subjectID", nativeQuery = true)
    void deleteMark(int id, int subjectID);
//    @Query(value = """
//            SELECT CASE WHEN EXISTS
//            (
//                    SELECT * FROM marks WHERE st_id = :studentID AND subject_id = :subjectID
//            )
//            THEN 'TRUE'
//            ELSE 'FALSE'
//            END
//            """
//    ,nativeQuery = true)
//    boolean isNEW(int studentID, int subjectID);
//
    @Query(value = "SELECT COUNT(*) FROM marks WHERE st_id= :studentID AND subject_id = :subjectID",nativeQuery = true)
    int isNEW(@Param("studentID") int studentID,@Param("subjectID") int subjectID);

    @Query(value = "SELECT COUNT(*) FROM marks WHERE st_id IN :studentIDList AND subject_id IN :studentSubjectList",nativeQuery = true)
    int isNEWList(List<Integer> studentIDList, List<Integer> studentSubjectList);

    //    @Query(value = "SELECT COUNT(*) FROM marks WHERE st_id IN :studentIDS AND subject_id IN subjectIDS",nativeQuery = true)
//    boolean isNew(List<Integer> studentIDS,List<Integer> subjectIDS);
//    @Query(value = "SELECT * FROM students where (:search is null or name like concat('%',:search,'%'))")
    @Query(value = "SELECT * FROM marks WHERE (:subjectID is null or subject_id=:subjectID)",nativeQuery = true)
    Page<Mark> getAllMarks(Integer subjectID, Pageable pageParams);

//    @Query(value = "SELECT * FROM marks WHERE (?1 is null or subject_id = ?1)", nativeQuery = true)
//    Page<Mark> getAllMarks(Integer subjectID, Pageable pageParams);


    @Query(value = "SELECT COUNT(*) FROM marks WHERE id = :id",nativeQuery = true)
    int getIDCount(int id);

    @Query(value = "SELECT CONCAT(m.st_id, m.subject_id) from marks m",nativeQuery = true)
    List<String> getStudentMarkList();
}
