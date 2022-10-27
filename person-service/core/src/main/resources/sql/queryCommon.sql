select last_name, first_name, phone_number
from medical_schema.person_data as pd, medical_schema.contact as c where pd.contact_id=c.id;