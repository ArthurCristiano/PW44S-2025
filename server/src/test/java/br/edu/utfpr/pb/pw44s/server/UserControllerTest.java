package br.edu.utfpr.pb.pw44s.server;

import br.edu.utfpr.pb.pw44s.server.model.User;
import br.edu.utfpr.pb.pw44s.server.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    private static final String API_USERS = "/users";

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void postUser_whenUserIsValid_receiveOk(){
//        User user = createValidUser();
//        ResponseEntity<Object> response = restTemplate.postForEntity(API_USERS, user, Object.class);
//        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}
