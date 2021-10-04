package cn.zhgliu.ezdp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.zhgliu.ezdp.*.mapper")
public class EasyDataPermServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EasyDataPermServerApplication.class, args);
    }

}
