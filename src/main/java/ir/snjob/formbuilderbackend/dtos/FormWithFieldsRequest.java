package ir.snjob.formbuilderbackend.dtos;

import ir.snjob.formbuilderbackend.models.Field;
import ir.snjob.formbuilderbackend.models.Form;
import lombok.Data;

import java.util.List;

@Data
public class FormWithFieldsRequest {
    private Form form;
    private List<Field> fields;
}
