package app.jpa_repo;

import app.model.users.Person;
import org.atmosphere.config.service.Delete;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findById(long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET email = :emailparam WHERE id = :idparam ")
    void updateEmailById(@Param("idparam") long id, @Param("emailparam") String email);

    @Modifying
    @Transactional
    @Query(value = " UPDATE users SET email = :emailparam , username = :usernameparam , firstName = :firstnameparam , lastName = :lastnameparam , description = :descriptionparam , role = :roleparam , website = :websiteparam  WHERE id = :idparam")
    void updateUserById(@Param("idparam") long id, @Param("emailparam") String email, @Param("usernameparam") String username , @Param("firstnameparam") String firstname, @Param("lastnameparam") String lastname, @Param("descriptionparam") String description, @Param("roleparam") Person.Role role, @Param("websiteparam") String website);

    @Modifying
    @Transactional
    @Query(value =" DELETE FROM direct_messages WHERE useridfrom = :idparam AND useridto = :idparam",nativeQuery = true)
    void deleteUserByIdIndirect_messages(@Param("idparam") long id);

    @Modifying
    @Transactional
    @Query(value =" DELETE FROM group_members WHERE userid = :idparam",nativeQuery = true)
    void deleteUserByIdGroup_members(@Param("idparam") long id);

    @Modifying
    @Transactional
    @Query(value =" UPDATE posts SET userid = 1, deleted = 1 WHERE userid = :idparam", nativeQuery = true)
    void updateUserByIdPosts(@Param("idparam") long id);

    @Modifying
    @Transactional
    @Query(value =" DELETE FROM users WHERE id = :idparam ",nativeQuery = true)
    void deleteUserById(@Param("idparam") long id);

    @Modifying
    @Transactional
    @Query(value =" DELETE FROM users WHERE username =''",nativeQuery = true)
    void deleteNullUsers();

    @Modifying
    @Transactional
    @Query(value = " INSERT INTO users (username ,password ,role ,firstName ,lastName ,email ,description ,website ,firstlogin ,lastlogin ,timecreated ) VALUES ( username ,password ,role ,firstname ,lastname ,email ,description ,website ,firstlogin ,lastlogin ,timecreated )",nativeQuery = true)
    void addUser(@Param("usernameparam") String username , @Param("passwordParam") String password, @Param("roleparam") Person.Role role,  @Param("firstnameparam") String firstname, @Param("lastnameparam") String lastname, @Param("emailparam") String email,@Param("descriptionparam") String description, @Param("websiteparam") String website,@Param("firstloginparam") long firstlogin,@Param("lastloginparam") long lastlogin,@Param("timecreatedparam") long timecreated);

    Person findByUsername(String username);

    ArrayList<Person> findAll();


}
