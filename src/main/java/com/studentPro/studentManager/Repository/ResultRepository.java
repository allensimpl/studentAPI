package com.studentPro.studentManager.Repository;

import com.studentPro.studentManager.view.IStudentMarkView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends JpaRepository<IStudentMarkView,Integer> {
    @Query(value = """
           SELECT
           S.name AS studentName,
           Sub.subject AS SubjectName,
           M.mark AS Mark
           FROM marks M
           JOIN students S ON M.st_id = S.id
           JOIN subjects Sub ON M.subject_id = Sub.id
           ORDER BY
           M.mark DESC;
        """, nativeQuery = true)
    public List<Result> getResults();
}
