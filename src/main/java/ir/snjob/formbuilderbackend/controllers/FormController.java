package ir.snjob.formbuilderbackend.controllers;

import ir.snjob.formbuilderbackend.dtos.FormWithFieldsRequest;
import ir.snjob.formbuilderbackend.dtos.FormWithFieldsResponse;
import ir.snjob.formbuilderbackend.models.Form;
import ir.snjob.formbuilderbackend.models.Field;
import ir.snjob.formbuilderbackend.services.FormService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/forms")
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    // Retrieve all forms
    @GetMapping
    public List<Form> getAllForms() {
        return formService.getAllForms();
    }

    // Create a form with fields
    @PostMapping
    public FormWithFieldsResponse createFormWithFields(@RequestBody FormWithFieldsRequest request) {
        return formService.createFormWithFields(request);
    }

    // Retrieve a specific form by ID
    @GetMapping("/{id}")
    public FormWithFieldsResponse getFormWithFields(@PathVariable Long id) {
        return formService.getFormWithFields(id);
    }

    // Update a form
    @PutMapping("/{id}")
    public Form updateForm(@PathVariable Long id, @RequestBody Form updatedForm) {
        return formService.updateForm(id, updatedForm);
    }

    // Delete a form and its fields
    @DeleteMapping("/{id}")
    public void deleteForm(@PathVariable Long id) {
        formService.deleteForm(id);
    }

    // Toggle publish status of a form
    @PostMapping("/{id}/publish")
    public void togglePublish(@PathVariable Long id) {
        formService.togglePublish(id);
    }

    // Retrieve all published forms
    @GetMapping("/published")
    public List<Form> getPublishedForms() {
        return formService.getPublishedForms();
    }

    // Retrieve fields for a specific form
    @GetMapping("/{id}/fields")
    public List<Field> getFieldsByFormId(@PathVariable Long id) {
        return formService.getFieldsByFormId(id);
    }

    @PutMapping("/{id}/fields")
    public List<Field> updateFieldsByFormId(@PathVariable Long id, @RequestBody List<Field> updatedFields) {
        return formService.updateFieldsByFormId(id, updatedFields);
    }

}
