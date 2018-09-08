package no.fint.customer.mailinglist.provisioning

import no.fint.customer.mailinglist.provisioning.service.GoogleMailingListService
import no.fint.portal.model.contact.Contact
import no.fint.portal.model.contact.ContactService
import spock.lang.Specification

class SchedulerSpec extends Specification {

    def "Provisioning Mailing List"() {
        given:
        def googleMailingListService = Mock(GoogleMailingListService)
        def contactService = Mock(ContactService)
        def scheduler = new Scheduler(googleMailingListService: googleMailingListService, contactService: contactService)

        when:
        scheduler.provisioningMailingList()

        then:
        1 * contactService.getContacts() >> Arrays.asList(new Contact(mail: "test1@test.com"), new Contact(mail: "test2@test.com"))
        1 * googleMailingListService.updateMailingList(_ as List<Contact>)

    }
}
