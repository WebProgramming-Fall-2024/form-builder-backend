package ir.snjob.formbuilderbackend.repositories;

import ir.snjob.formbuilderbackend.models.Field;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FieldRepository extends JpaRepository<Field, Long> {
    List<Field> findByFormId(Long formId);
}
