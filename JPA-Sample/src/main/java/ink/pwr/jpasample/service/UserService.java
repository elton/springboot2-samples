package ink.pwr.jpasample.service;

import ink.pwr.jpasample.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
  User getUser(Long id);

  List<User> getUsers();

  User createUser(User user);

  void deleteUser(Long id);

  Page<User> findAll(Pageable pageable);

  List<User> findByNameLike(String name);
}
