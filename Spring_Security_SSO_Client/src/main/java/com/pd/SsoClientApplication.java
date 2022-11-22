import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * @author: pd
 * @date: 2021-02-18 下午12:02
 */
@EnableOAuth2Sso
@SpringBootApplication
public class SSOClientApplication {
  public static void main(String[] args) {
    SpringApplication.run(SSOClientApplication.class, args);
  }
}
