/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dalcart.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.slf4j.Log4jLoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.*;

@SpringBootApplication
public class Main {

  // @Value("${spring.datasource.url}")
  // private String dbUrl;

  // @Value("${spring.datasource.username}")
  // private String username;

  // @Value("${spring.datasource.password}")
  // private String password;

  // @Autowired
  // private DataSource dataSource;

  public static void main(String[] args) {
      SpringApplication.run(Main.class, args);
  }

  @RequestMapping("/")
  String index() {
    return "index";
  }


  // @RequestMapping("/get_product_name")
  // String admin() {
  //     return "admin";
  // }

  @GetMapping("/get_product_name")
  @ResponseBody
  String db(@RequestParam String id, @RequestParam String userid, @RequestParam String email) {
    System.out.println(id);
    System.out.println(userid);
    System.out.println(email);
   HashMap<String, String> map = new HashMap<>();
    map.put("key", "value");
    map.put("foo", "bar");
    map.put("aa", "bb");
    //return "{\"product\":\"iphone\"}";
   // String json = new ObjectMapper().writeValueAsString(map);
    //return json;
    String json = "";
    ObjectMapper objectMapper = new ObjectMapper();
    try {
        json = objectMapper.writeValueAsString(map);
        System.out.println(json);
    } catch (Exception e) {
        e.printStackTrace();
    }

    return json;


    //return "{\"name\":\"product\"}";
    }

  // @Bean
  // public DataSource dataSource() throws SQLException {
  //   if (dbUrl == null || dbUrl.isEmpty()) {
  //     return new HikariDataSource();
  //   } else {
  //     HikariConfig config = new HikariConfig();
  //     config.setJdbcUrl(dbUrl);
  //     config.setUsername(username);
  //     config.setPassword(password);
  //     return new HikariDataSource(config);
  //   }
  // }

}
