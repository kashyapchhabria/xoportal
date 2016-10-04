package com.xo.web.util;

import java.util.ArrayList;
import java.util.Collection;

import play.Logger;

import com.feth.play.module.mail.Mailer;
import com.feth.play.module.mail.Mailer.Mail.Body;
import com.xo.web.core.XOException;
import com.xo.web.viewdtos.MailDto;

public class XoMailSender extends XoAsynchTask {

	public static final Mailer XO_MAILER = Mailer.getDefaultMailer();
	private static final String MAIL_HEADER_VALUE_BULK = "bulk";
	private static final String MAIL_HEADER_KEY_PRECEDENCE = "Precedence";
	private final Collection<MailDto> mailDtos;

	public XoMailSender(final Collection<MailDto> mailDtos) {
		super("XODefault Mail Sender");
		this.mailDtos = mailDtos;
		XoAsyncTaskHandler.submitAsynchTask(this);
	}

	public XoMailSender(final MailDto mailDto) {
		super("XODefault Mail Sender");
		this.mailDtos = new ArrayList<MailDto>(1);
		this.mailDtos.add(mailDto);
		XoAsyncTaskHandler.submitAsynchTask(this);
	}
	
	public void process() throws Throwable {
		this.sendMail();
	}

	private void sendMail() throws XOException {
		if(XoUtil.hasData(this.mailDtos)) {
			for(MailDto mailDto : this.mailDtos) {
				this.sendMail(mailDto);
			}
			//mailDtos.clear();
		}
	}

	private void sendMail(final MailDto mailDto) throws XOException {
		try{
			if(mailDto != null && XoUtil.hasData(mailDto.emailIds)) {
				
				final Body body = new Body("", mailDto.message);
				int sentMailCount = 0;
				for(String recipient : mailDto.emailIds) {
					if(sentMailCount == 5) {
						try {
							Thread.sleep(5000);
						} catch(InterruptedException ex) {
							Logger.error("Error occurred while waiting to send mails.", ex);
						}
					}
					final Mailer.Mail mail = new Mailer.Mail(mailDto.subject, body, new String[]{recipient});
					mail.addCustomHeader(MAIL_HEADER_KEY_PRECEDENCE, MAIL_HEADER_VALUE_BULK);
					XO_MAILER.sendMail(mail);
					sentMailCount++;
				}
			}
		} catch(Exception e) {
			Logger.error("Error while sending mail.", e);
			throw new XOException("Mail sending error.", e);
		}
	}
}
