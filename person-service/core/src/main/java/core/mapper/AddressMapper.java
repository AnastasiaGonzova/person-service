package core.mapper;

import core.model.Address;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AddressMapper {

    @Select("Select * from address where id=#{id}")
    Address get(Long Id);

    @Insert("Insert into address(id, country_id, city, post_code, street, building, flat) values" +
            "(#{id}, #{countryId}, #{city}, #{postCode}, #{street}, #{building}), #{flat}")
    Address create(Address addressJson);

    @Update("Update address set last_name=#{lastName}, first_name=#{firstName}, birth_dt=#{birthDt}, age=#{age}, sex=#{sex}" +
            "where id=#{addressId}")
    Address update(Long addressId, Address addressJson);

    @Delete("Delete from address where id=#{id}")
    void delete(Long id);

    @Update("Update address set contact_id=#{contactId} where id=#{addressId}")
    Address assignContact(Long addressId, Long contactId);
}
