create view medical_schema.person_contacts as
(
    select last_name, first_name, phone_number, email, city
    from medical_schema.person_data, medical_schema.contact, medical_schema.address
    where person_data.contact_id = contact.id and address.contact_id = contact.id
)