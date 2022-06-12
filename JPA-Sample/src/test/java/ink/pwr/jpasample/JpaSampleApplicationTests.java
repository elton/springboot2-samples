package ink.pwr.jpasample;

import ink.pwr.jpasample.entity.User;
import ink.pwr.jpasample.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
class JpaSampleApplicationTests {

  @Resource private UserService userService;

  @Test
  void contextLoads() {
    // Query All users
    userService.getUsers().forEach(user -> log.info("User: {}", user));

    // Insert some users
    // Lombok 的 @Builder 注解可以自动生成一个 builder 类，
    userService.createUser(User.builder().name("Sam").email("sam@some.com").age(20).build());
    userService.createUser(User.builder().name("Bob").email("bob@some.com").age(30).build());
    userService.createUser(User.builder().name("Lisa").email("lisa@some.com").age(40).build());

    // Pageable query, param page is zero based.
    userService.findAll(PageRequest.of(0, 2)).stream()
        .forEach(user -> log.info("User: {}", user.getId()));

    // fuzzy query
    List<User> users = userService.findByNameLike("%b%");
    log.info("Fuzzy query: {}", users);

    // Update user
    User bob = userService.getUser(users.get(0).getId());
    bob.setName("Bob Updated");
    userService.createUser(bob);

    // Delete user
    userService.deleteUser(bob.getId());
  }
}
