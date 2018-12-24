import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
public class Demo1 {
    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    @Test
    public void test1(){
        String encode = encoder.encode("123");
        //$2a$10$J1ec0.7Sdh0s6nH2ibRh5e9aDLw2uGyroQkIjmPJbp0d08nR/.O26
        //$2a$10$NJKE95l9zPELNN0lQp5KZuq42J1IZm6VNPn4AnM1RQwl6orKm1dG.
        System.out.println(encode);
    }
}
