package ink.pwr.jpasample.entity;

import ink.pwr.jpasample.config.SnowflakeIDGeneratorConfig;
import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
// 启用 JPA Auditing, 可以自动记录创建和更新的时间.
// https://stackoverflow.com/questions/40370709/createddate-annotation-does-not-work-with-mysql
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
// 自动生成一个 builder 类，
@Builder(toBuilder = true)
@Setter
@Getter
public class User {
  @Id
  @GeneratedValue(
      generator = SnowflakeIDGeneratorConfig.ID_GENERATOR_NAME,
      strategy = GenerationType.SEQUENCE)
  @GenericGenerator(
      name = SnowflakeIDGeneratorConfig.ID_GENERATOR_NAME,
      strategy = "ink.pwr.jpasample.config.SnowflakeIDGeneratorConfig")
  private Long id;

  private String name;
  private String email;
  private Integer age;

  @CreatedDate
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;

  @LastModifiedDate
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updatedAt;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    User user = (User) o;
    return id != null && Objects.equals(id, user.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
