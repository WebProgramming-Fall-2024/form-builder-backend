## **Form Builder backend**

### **Problem Statement**

Almost everyone has worked with Google Forms at least once. It’s an application that allows you to create and publish
custom forms. In this small exercise, we are not going to build a comprehensive form builder. Instead, we aim to create
a simplified model of it.

We will design a small REST API that allows us to create forms and manage their data through a client application. The
client should be able to retrieve the data and display it appropriately.

### **Data Models**

This program includes a **Form** class with the following attributes:

1. A **unique identifier** (ID).
2. A **name**.
3. A **publish status** (whether the form is published or unpublished).
4. A **set of fields** (each field should be an object of the Field class).
5. A **Submit** button at the end (with methods and endpoints defined).

Each **Field** must include the following attributes:

- A **unique ID** (unique within the form).
- A **label**.
- A **field name**.
- A **type** (e.g., text, number).
- A **default value**.

### **Supported Field Types**

The program must support at least the following field types:

1. **Text**
2. **Number**
3. **Boolean** (Checkbox)
4. **Date**

### **REST API Endpoints**

The REST API must include endpoints for managing forms, as shown in the table below:

| Endpoint Path                 | HTTP Method | Description                                                                 |
|-------------------------------|-------------|-----------------------------------------------------------------------------|
| /forms/                      | GET         | Retrieve a list of all forms.                                               |
| /forms/                      | POST        | Create a new form.                                                          |
| /forms/{id}                  | GET         | Retrieve a form by its ID.                                                  |
| /forms/{id}                  | PUT         | Update the information of a specific form.                                  |
| /forms/{id}                  | DELETE      | Delete a form.                                                              |
| /forms/{id}/fields           | GET         | View the fields of a specific form.                                         |
| /forms/{id}/fields           | PUT         | Update the fields of a form.                                                |
| /forms/{id}/publish          | POST        | Toggle the publication status of a form. If published, set it to unpublished and vice-versa. |
| /forms/{id}/published        | GET         | Retrieve a list of published forms.                                         |

### **Database Requirements**

Forms must be stored and retrieved from a database. The relationships between forms and fields should be managed
properly. For this purpose, **Spring Data** should be used to ensure proper entity management.

### **Important Notes**

1. **General Requirements**:
    - Handle errors gracefully.
    - Use **DTOs (Data Transfer Objects)** for communication between the client and server.
    - Leverage **Spring Data** for database design and implementation.
    - Implement other required functionalities as needed.

2. **Backend Only**:
    - This project is strictly **backend-focused** and does **not require frontend implementation**.
    - Use tools like **Postman** to test the program if necessary. 
