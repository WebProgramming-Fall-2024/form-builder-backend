package ir.snjob.formbuilderbackend.dtos;

import ir.snjob.formbuilderbackend.models.Field;
import ir.snjob.formbuilderbackend.models.Form;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class FormWithFieldsResponse {
    private Form form;
    private List<Field> fields;
}
