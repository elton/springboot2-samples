package ink.pwr.jpasample.config;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import javax.annotation.PostConstruct;
import java.io.Serializable;

@Slf4j
public class SnowflakeIDGeneratorConfig implements IdentifierGenerator {
  public static final String ID_GENERATOR_NAME = "snowflake_id_generator";

  // 终端ID
  public static long WORKER_ID = 1;
  // 数据中心ID
  public static long DATA_CENTER_ID = 1;

  // 使用IdUtil.getSnowflake使用全局单例对象。
  public Snowflake snowFlake = IdUtil.getSnowflake(WORKER_ID, DATA_CENTER_ID);

  @PostConstruct
  public void init() {
    log.info("SnowflakeIDGeneratorConfig init");
    // 规定workerId范围在0L-31L之间，超出会报异常， 所以加上 `>> 16 & 31`,
    // https://blog.csdn.net/qq_43232369/article/details/123144021
    WORKER_ID = NetUtil.ipv4ToLong(NetUtil.getLocalhostStr()) >> 16 & 31;
    log.info("Current machine's WORKER_ID: {}", WORKER_ID);
  }

  public synchronized long snowflakeId() {
    return snowFlake.nextId();
  }

  public synchronized long snowflakeId(long workerId, long dataCenterId) {
    return IdUtil.getSnowflake(workerId, dataCenterId).nextId();
  }

  @Override
  public Serializable generate(SharedSessionContractImplementor session, Object obj) {
    return snowflakeId(WORKER_ID, DATA_CENTER_ID);
  }
}
