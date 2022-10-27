select * from medical_schema.person_data as pd
         order by pd.id limit (select count(*) from medical_schema.person_data)/2