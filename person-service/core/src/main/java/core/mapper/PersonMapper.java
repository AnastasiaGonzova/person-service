package core.mapper;

import core.model.PersonData;
import org.apache.ibatis.annotations.*;

@Mapper
public interface PersonMapper {

    @Select("Select * from person_data where id=#{id}")
    PersonData get(Long Id);

    @Insert("Insert into person_data(id, last_name, first_name, birth_dt, age, sex) values" +
            "(#{id}, #{lastName}, #{firstName}, #{birthDt}, #{age}, #{sex})")
    PersonData create(PersonData personDataJson);

    @Update("Update person_data set last_name=#{lastName}, first_name=#{firstName}, birth_dt=#{birthDt}, age=#{age}, sex=#{sex}" +
            "where id=#{id}")
    PersonData update(Long Id, PersonData personDataJson);

    @Delete("Delete from person_data where id=#{id}")
    void delete(Long id);

    @Update("Update person_data set contact_id=#{contactId} where id=#{personId}")
    PersonData assignContact(Long personId, Long contactId);

    @Update("Update person_data set medical_card_id=#{medicalCardId} where id=#{personId}")
    PersonData assignMedicalCard(Long personId, Long medicalCardId);

    @Update("Update person_data set parent_id=#{parentId} where id=#{personId} and #{parentId} <> id")
    PersonData assignParent(Long personId, Long parentId);

}
