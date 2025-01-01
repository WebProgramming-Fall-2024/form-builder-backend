package ir.snjob.formbuilderbackend.services;

import ir.snjob.formbuilderbackend.dtos.FormWithFieldsRequest;
import ir.snjob.formbuilderbackend.dtos.FormWithFieldsResponse;
import ir.snjob.formbuilderbackend.models.Field;
import ir.snjob.formbuilderbackend.models.Form;
import ir.snjob.formbuilderbackend.repositories.FieldRepository;
import ir.snjob.formbuilderbackend.repositories.FormRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FormService {

    private final FormRepository formRepository;
    private final FieldRepository fieldRepository;

    public FormService(FormRepository formRepository, FieldRepository fieldRepository) {
        this.formRepository = formRepository;
        this.fieldRepository = fieldRepository;
    }

    // Create a form with fields
    @Transactional
    public FormWithFieldsResponse createFormWithFields(FormWithFieldsRequest request) {
        Form form = request.getForm();
        List<Field> fields = request.getFields();

        // Save form
        Form savedForm = formRepository.save(form);

        // Assign form ID to fields and save
        fields.forEach(field -> field.setFormId(savedForm.getId()));
        List<Field> savedFields = fieldRepository.saveAll(fields);

        return new FormWithFieldsResponse(savedForm, savedFields);
    }

    // Retrieve a form by ID with its fields
    @Transactional(readOnly = true)
    public FormWithFieldsResponse getFormWithFields(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Form not found"));

        List<Field> fields = fieldRepository.findByFormId(id);

        return new FormWithFieldsResponse(form, fields);
    }

    // Retrieve all forms
    public List<Form> getAllForms() {
        return formRepository.findAll();
    }

    // Update form details
    @Transactional
    public FormWithFieldsResponse updateFormWithFields(Long id, FormWithFieldsRequest request) {
        // Update form details
        Form updatedForm = formRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Form not found"));

        updatedForm.setName(request.getForm().getName());
        updatedForm.setPublished(request.getForm().isPublished());
        Form savedForm = formRepository.save(updatedForm);

        // Update fields
        fieldRepository.deleteAll(fieldRepository.findByFormId(id)); // Clear old fields
        List<Field> updatedFields = request.getFields();
        updatedFields.forEach(field -> field.setFormId(savedForm.getId()));
        List<Field> savedFields = fieldRepository.saveAll(updatedFields);

        return new FormWithFieldsResponse(savedForm, savedFields);
    }

    @Transactional
    public Form updateForm(Long id, Form updatedForm) {
        // Find the form by ID or throw an exception if not found
        Form existingForm = formRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Form not found"));

        // Update the form's name and published status
        if (updatedForm.getName() != null) {
            existingForm.setName(updatedForm.getName());
        }
        existingForm.setPublished(updatedForm.isPublished());

        // Save the updated form and return it
        return formRepository.save(existingForm);
    }


    // Delete a form and its fields
    @Transactional
    public void deleteForm(Long id) {
        fieldRepository.deleteAll(fieldRepository.findByFormId(id)); // Delete fields first
        formRepository.deleteById(id);
    }

    // Publish or unpublish a form
    @Transactional
    public void togglePublish(Long id) {
        Form form = formRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Form not found"));
        form.setPublished(!form.isPublished());
        formRepository.save(form);
    }

    // Retrieve all published forms
    public List<Form> getPublishedForms() {
        return formRepository.findByPublished(true);
    }

    // Get fields by form ID
    public List<Field> getFieldsByFormId(Long formId) {
        return fieldRepository.findByFormId(formId);
    }

    @Transactional
    public List<Field> updateFieldsByFormId(Long formId, List<Field> updatedFields) {
        // Delete all existing fields for the given form ID
        fieldRepository.deleteAll(fieldRepository.findByFormId(formId));

        // Assign the correct form ID to the updated fields and save them
        updatedFields.forEach(field -> field.setFormId(formId));
        return fieldRepository.saveAll(updatedFields);
    }

}
