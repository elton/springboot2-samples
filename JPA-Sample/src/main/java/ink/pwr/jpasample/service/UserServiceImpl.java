package ink.pwr.jpasample.service;

import ink.pwr.jpasample.entity.User;
import ink.pwr.jpasample.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
// @Service 注解用在服务层，和 @Component 注解作用类似（通用注解），Spring Boot 会自动扫描该类注解的类，并把它们加入到 Spring 容器中。
@Service
public class UserServiceImpl implements UserService {

  // @Resource  是 byName 自动装配，@Autowired  是 byType 自动装配，当有两个类型完全一样的对象时，@Autowired  就会出错了。
  @Resource private UserRepository userRepository;

  @Override
  public User getUser(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  @Override
  public List<User> getUsers() {
    return userRepository.findAll();
  }

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  @Override
  //  Pageable 是 Spring 提供的一个分页查询接口，查询的时候只需要传入一个 Pageable 接口的实现类，指定第几页（pageNumber）和页面大小（pageSize）即可。
  // Page 是 Spring 提供的一个分页返回结果接口。
  public Page<User> findAll(Pageable pageable) {
    return userRepository.findAll(pageable);
  }

  @Override
  public List<User> findByNameLike(String name) {
    return userRepository.findByNameLike(name);
  }
}
