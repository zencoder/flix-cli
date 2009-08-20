package tv.zencoder.flix.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class MailUtil {
    public static void emailDebugMessage(String msg) {
	SimpleEmail email = new SimpleEmail();
	email.setHostName("smtp.gmail.com");
	email.setAuthentication("debug@zencoder.tv", "ssa8Jm4gsqdUCr");
	email.setTLS(true);
	email.setSSL(true);
	try {
	    email.addTo("jdl@zencoder.tv", "JDL");
	    email.setFrom("debug@zencoder.tv", "debugger");
	    email.setSubject("[Flix Debug] debug message from flix worker");
	    email.setMsg(msg);
	    email.send();
	} catch (EmailException e) {
	    e.printStackTrace();
	}
    }

}
