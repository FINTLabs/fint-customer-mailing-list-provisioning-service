package no.fint.customer.mailinglist.provisioning;


import lombok.extern.slf4j.Slf4j;
import no.fint.customer.mailinglist.provisioning.service.GoogleMailingListService;
import no.fint.portal.model.contact.Contact;
import no.fint.portal.model.contact.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Slf4j
@Component
public class Scheduler {

    @Autowired
    private GoogleMailingListService googleMailingListService;

    @Autowired
    private ContactService contactService;

    @Scheduled(fixedDelayString = "${fint.customer.mailinglist.provisioning.interval:50000}")
    public void provisioningMailingList() throws GeneralSecurityException, IOException {
        log.info("Start provisioning email list...");
        List<Contact> contacts = contactService.getContacts();
        googleMailingListService.updateMailingList(contacts);
        log.info("End provisioning email list.");

    }

}
