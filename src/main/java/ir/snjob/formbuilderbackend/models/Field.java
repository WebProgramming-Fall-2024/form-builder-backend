package ir.snjob.formbuilderbackend.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Field {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String label;
    private String name;
    private String type;
    private String defaultValue;

    @Column(name = "form_id")
    private Long formId;
}
