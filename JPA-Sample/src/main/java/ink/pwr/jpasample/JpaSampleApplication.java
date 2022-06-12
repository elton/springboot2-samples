package ink.pwr.jpasample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
// 启用 JPA Auditing, 可以自动记录创建和更新的时间.
// https://stackoverflow.com/questions/40370709/createddate-annotation-does-not-work-with-mysql
@EnableJpaAuditing
public class JpaSampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(JpaSampleApplication.class, args);
  }
}
