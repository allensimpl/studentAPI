package com.studentPro.studentManager.Repository;

import ch.qos.logback.core.boolex.EvaluationException;
import com.studentPro.studentManager.Entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Integer> {
    @Query(value = "SELECT COUNT(*) FROM subjects WHERE id = :id",nativeQuery = true)
    public int containsID(int id);

    @Query(value = "SELECT COUNT(*) FROM subjects WHERE subject = :subject",nativeQuery = true)
    public int containsSubject(String subject);

    @Query(value = "SELECT COUNT(*) FROM subjects WHERE subject in :subjectsList",nativeQuery = true)
    public int containsSubjects(List<String> subjectsList);
}
