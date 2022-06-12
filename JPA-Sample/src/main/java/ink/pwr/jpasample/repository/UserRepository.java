package ink.pwr.jpasample.repository;

import ink.pwr.jpasample.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
// extends JpaRepository<User, Long>, first param is the entity class, second is the primary key
// type
public interface UserRepository extends JpaRepository<User, Long> {

  @Query("select u from User u where u.name like concat('%',:name,'%') ")
  List<User> findByNameLike(@Param("name") String name);
}
