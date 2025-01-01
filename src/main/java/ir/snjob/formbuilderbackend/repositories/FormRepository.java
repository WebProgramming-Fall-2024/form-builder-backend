package ir.snjob.formbuilderbackend.repositories;

import ir.snjob.formbuilderbackend.models.Form;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FormRepository extends JpaRepository<Form, Long> {
    List<Form> findByPublished(boolean published);
}
