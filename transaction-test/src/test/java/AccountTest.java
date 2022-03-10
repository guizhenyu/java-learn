import io.guizhenyu.transaction.test.service.TcTestApp;
import io.guizhenyu.transaction.test.service.repository.model.CmAccount;
import io.guizhenyu.transaction.test.service.service.AService;
import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * description: AccountTest date: 2022/2/12 12:23 下午
 *
 * @author: guizhenyu
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TcTestApp.class)
public class AccountTest {


  @Autowired
  private AService service;

  @Test
  public void addTest(){
    CmAccount cmAccount = new CmAccount();

    cmAccount.setUsername("001");
    cmAccount.setDeleted("0");
    cmAccount.setLastLoginTime(new Date());
    cmAccount.setCreateTime(new Date());
    cmAccount.setUpdateTime(new Date());
    cmAccount.setDescription("sdfgs");
    cmAccount.setEmail("sdfsg");
    cmAccount.setMobile("134645");
    cmAccount.setSalt("df");
    cmAccount.setPassword("12e4325");
    cmAccount.setSex("0");
    cmAccount.setCreateBy("");
    cmAccount.setUpdateBy("");
    service.insert(cmAccount);
  }
}
