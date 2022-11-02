package core.mapper;

import core.model.Contact;
import org.apache.ibatis.annotations.*;

@Mapper
public interface ContactMapper {

    @Select("Select * from contact where id=#{id}")
    Contact get(Long Id);

    @Insert("Insert into contact(id, phone_number, email, profile_link) values" +
            "(#{id}, #{phoneNumber}, #{email}, #{profileLink})")
    Contact create(Contact contactJson);

    @Update("Update contact set phone_number=#{phoneNumber}, email=#{email}, profile_link=#{profileLink}" +
            "where id=#{contactId}")
    Contact update(Long contactId, Contact contactJson);

    @Delete("Delete from contact where id=#{id}")
    void delete(Long id);
}
