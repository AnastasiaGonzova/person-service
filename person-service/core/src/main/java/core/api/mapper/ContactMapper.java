package core.api.mapper;

import core.model.Contact;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ContactMapper {

    @Select("Select id, phone_number, email, profile_link from contact where id=#{contactId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property="phoneNumber", column = "phone_number"),
            @Result(property="email", column = "email"),
            @Result(property="profileLink", column = "profile_link"),
    })
    Contact get(Long contactId);

    @Insert("Insert into contact(id, phone_number, email, profile_link) values" +
            "(#{id}, #{phoneNumber}, #{email}, #{profileLink})")
    Integer create(Contact contactJson);

    @Update("Update contact set phone_number=#{contactJson.phoneNumber}, email=#{contactJson.email}, " +
            "profile_link=#{contactJson.profileLink} where id=#{contactId}")
    void update(Long contactId, Contact contactJson);

    @Delete("Delete from contact where id=#{contactId}")
    void delete(Long contactId);
}
