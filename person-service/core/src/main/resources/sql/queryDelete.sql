delete from medical_schema.medical_card where medical_card.id =
    (
        select max(id) from medical_schema.medical_card inner join
            (
                select client_status, med_status, registry_dt, comment_about
                from medical_schema.medical_card group by client_status, med_status, registry_dt, comment_about
                having COUNT(id) > 1
            ) as dup
            on medical_card.client_status = dup.client_status and
               medical_card.med_status = dup.med_status and
               medical_card.registry_dt = dup.registry_dt and
               medical_card.comment_about = dup.comment_about
    );