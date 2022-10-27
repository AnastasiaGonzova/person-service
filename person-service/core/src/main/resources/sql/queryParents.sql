select pd_person.last_name, pd_person.first_name, pd_parent.last_name, pd_parent.first_name
from medical_schema.person_data as pd_person
    inner join
    (
       select id, last_name, first_name from medical_schema.person_data
    ) as pd_parent
    on pd_person.parent_id = pd_parent.id
    inner join
    (
       select id, med_status from medical_schema.medical_card
    ) as mc
    on mc.id = pd_person.medical_card_id and
       mc.med_status is null