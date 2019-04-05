package com.zuehlke.testing.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Mailbox {

    private final MailServer mailServer;
    private final UserRepository userRepository;
    private List<Mail> mails;

    public Mailbox(MailServer mailServer, UserRepository userRepository) {
        this.mailServer = mailServer;
        this.userRepository = userRepository;
    }

    @Deprecated
    public Mailbox(String userId) {
        this(new MailServer(), new UserRepository());
        retrieveMailsForUser(userId);
    }

    public void retrieveMailsForUser(String userId) {
        this.mails = mailServer.getMailsForUser(userId).stream()
                .filter(isRelevantAndFromSafeSender())
                .collect(Collectors.toList());
    }

    private Predicate<Mail> isRelevantAndFromSafeSender() {
        return mail -> !mail.getSubject().contains("Junk")
                && !mail.getSubject().contains("Spam")
                && !mail.getSubject().contains("Advertisment")
                && !mail.getSubject().contains("Ads")
                || userRepository.isSafeSender(mail.getFrom());
    }


    public void sendMail(Mail mail) {
        mail.setDate(getCurrentDateTimeMillis());
        mailServer.sendMail(mail);
    }

    // package private so it can be overwritten by test class and return fixed value
    long getCurrentDateTimeMillis() {
        return System.currentTimeMillis();
    }

    static class MailServer {

        void sendMail(Mail mail) {
        }

        List<Mail> getMailsForUser(String userId) {
            return new ArrayList<>();
        }
    }

    class User {
    }

    static class UserRepository {

        public boolean isSafeSender(String from) {
            return true;
        }
    }


    class Mail {
        void setDate(long currentDate) {
        }

        String getFrom() {
            return "";
        }

        String getSubject() {
            return "";
        }
    }
}
