package core.api.mapper;

import core.model.Address;
import core.model.Contact;
import core.model.MedicalCard;
import org.apache.ibatis.annotations.*;

import java.util.Set;

@Mapper
public interface AddressMapper {

    @Select("Select * from address where id=#{addressId}")
    @Results(value = {
            @Result(property = "id", column = "id"),
            @Result(property="countryId", column = "country_id"),
            @Result(property="city", column = "city"),
            @Result(property="postCode", column = "post_code"),
            @Result(property="street", column = "street"),
            @Result(property="building", column = "building"),
            @Result(property="flat", column = "flat"),
            @Result(property ="contacts", javaType = Set.class,
                    column = "id", many=@Many(select = "getContacts"))
    })
    Address get(Long addressId);

    @Insert("Insert into address(id, country_id, city, post_code, street, building, flat) values" +
            "(#{id}, #{countryId}, #{city}, #{postCode}, #{street}, #{building}), #{flat}")
    Integer create(Address addressJson);

    @Update("Update address set last_name=#{lastName}, first_name=#{firstName}, birth_dt=#{birthDt}, age=#{age}, sex=#{sex}" +
            "where id=#{addressId}")
    void update(Long addressId, Address addressJson);

    @Delete("Delete from address where id=#{id}")
    void delete(Long id);

    @Update("Update address set contact_id=#{contactId} where id=#{addressId}")
    void assignContact(Long addressId, Long contactId);

    @Select("Select ct.id, phone_number, email, profile_link " +
            "from contact as ct, address as ad " +
            "where ad.id=#{addressId} and ad.contact_id=ct.id")
    @Results(value = {
            @Result(property = "id", column="ct.id"),
            @Result(property = "phoneNumber", column="phone_number"),
            @Result(property = "email", column="email"),
            @Result(property = "profileLink", column="profile_link")
    })
    Set<Contact> getContacts(Long addressId);
}
