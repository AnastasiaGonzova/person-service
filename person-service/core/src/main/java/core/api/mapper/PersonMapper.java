package core.api.mapper;

import core.model.Contact;
import core.model.MedicalCard;
import core.model.PersonData;
import org.apache.ibatis.annotations.*;

import java.util.Set;

@Mapper
public interface PersonMapper {

    @Select("Select id, last_name, first_name, birth_dt, age, sex, contact_id, medical_card_id, parent_id" +
            " from person_data where id=#{personId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property="lastName", column = "last_name"),
            @Result(property="firstName", column = "first_name"),
            @Result(property="birthDt", column = "birth_dt"),
            @Result(property="age", column = "age"),
            @Result(property="sex", column = "sex"),
            @Result(property ="medicalCard", javaType = MedicalCard.class,
                    column = "id", one = @One(select = "getMedicalCard")),
            @Result(property ="contacts", javaType = Set.class,
                    column = "id", many=@Many(select = "getContacts")),
            @Result(property ="parents", javaType = Set.class,
                    column = "id", many=@Many(select = "getParents"))
    })
    PersonData get(Long personId);

    @Insert("Insert into person_data(id, last_name, first_name, birth_dt, age, sex) values" +
            "(#{id}, #{lastName}, #{firstName}, #{birthDt}, #{age}, #{sex})")
    Integer create(PersonData personDataJson);

    @Update("Update person_data set " +
            "last_name=#{personDataJson.lastName}, first_name=#{personDataJson.firstName}, " +
            "birth_dt=#{personDataJson.birthDt}, age=#{personDataJson.age}, sex=#{personDataJson.sex}" +
            "where id=#{personId}")
    void update(Long personId, PersonData personDataJson);

    @Delete("Delete from person_data where id=#{personId}")
    void delete(Long personId);

    @Update("Update person_data set contact_id=#{contactId} where id=#{personId}")
    void assignContact(Long personId, Long contactId);

    @Update("Update person_data set medical_card_id=#{medicalCardId} where id=#{personId}")
    void assignMedicalCard(Long personId, Long medicalCardId);

    @Update("Update person_data set parent_id=#{parentId} where id=#{personId} and #{parentId} <> id")
    void assignParent(Long personId, Long parentId);

    @Select("Select mc.id, client_status, med_status, registry_dt, comment_about " +
            "from medical_card as mc, person_data as pd " +
            "where pd.id=#{personId} and pd.medical_card_id=mc.id")
    @Results(value = {
            @Result(property = "id", column="mc.id"),
            @Result(property = "clientStatus", column="client_status"),
            @Result(property = "medStatus", column="med_status"),
            @Result(property = "registryDt", column="registry_dt"),
            @Result(property = "commentAbout", column="comment_about")
    })
    MedicalCard getMedicalCard(Long personId);

    @Select("Select ct.id, phone_number, email, profile_link " +
            "from contact as ct, person_data as pd " +
            "where pd.id=#{personId} and pd.contact_id=ct.id")
    @Results(value = {
            @Result(property = "id", column="ct.id"),
            @Result(property = "phoneNumber", column="phone_number"),
            @Result(property = "email", column="email"),
            @Result(property = "profileLink", column="profile_link")
    })
    Set<Contact> getContacts(Long personId);

    @Select("Select pr.id, pr.last_name, pr.first_name, pr.birth_dt, pr.age, pr.sex " +
            "from person_data as pd, person_data as pr " +
            "where pd.id=#{personId} and pd.parent_id=pr.id")
    @Results(value = {
            @Result(property = "id", column="pr.id"),
            @Result(property="lastName", column = "last_name"),
            @Result(property="firstName", column = "first_name"),
            @Result(property="birthDt", column = "birth_dt"),
            @Result(property="age", column = "age"),
            @Result(property="sex", column = "sex")
    })
    Set<PersonData> getParents(Long personId);




}
