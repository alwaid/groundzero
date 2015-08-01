import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alwaid.email.bean.EmailBean;
import com.alwaid.email.bean.IEmailBean;

public class main {
	public static void main(String[] args) {
		String pwd = System.getProperty("user.dir");
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"app-context.xml");
		IEmailBean emailBean = context.getBean(EmailBean.class);
		emailBean.setSubject("Test Mail");
		emailBean.sendMail();
		
		IEmailBean emailBean2 = context.getBean(EmailBean.class);
		emailBean2.setSubject("Test Mail 2");
		emailBean2.sendMail();
		
		System.out.println(emailBean.getSubject());
		System.out.println(emailBean2.getSubject());
	}
}
