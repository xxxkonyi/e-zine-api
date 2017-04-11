package com.sfkj.other.ezine;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@SpringBootApplication
//@Import(ServiceConfiguration.class)
@RestController
@Slf4j
public class MainApplication {

//    @RequestMapping("/")
//    public void index(HttpServletResponse response) throws IOException {
//        InetAddress inetAddress = InetAddress.getLocalHost();
//        response.getWriter().println("Server is live! hostName:" + inetAddress.getHostName());
//    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            log.info("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            Arrays.stream(beanNames).forEach(beanName -> log.info(beanName));

        };
    }


}

