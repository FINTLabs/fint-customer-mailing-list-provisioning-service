package no.fint.customer.mailinglist.provisioning.service


import com.google.api.services.admin.directory.model.Member
import no.fint.customer.mailinglist.provisioning.Props
import no.fint.portal.model.contact.Contact
import spock.lang.Specification

class GoogleMailingListServiceSpec extends Specification {

    private Props props
    private GoogleService googleService
    private GoogleMailingListService googleMailingListService
    private List<Contact> contacts
    private List<Member> members

    void setup() {
        props = new Props(credentialsFilePath: "file", applicationName: "test",
                serviceAccountUser: "test", mailingList: "test")
        googleService = Mock(GoogleService)
        googleMailingListService = new GoogleMailingListService(googleService: googleService, props: props)
        contacts = Arrays.asList(new Contact(mail: "test1@test.com"), new Contact(mail: "test2@test.com"))
        members = Arrays.asList(new Member(email: "test1@test.com"), new Member(email: "test3@test.com"))
    }

    def "Get Members To Add"() {

        when:
        def membersToAdd = googleMailingListService.getMembersToAdd(contacts, members)

        then:
        membersToAdd.size() == 1
        membersToAdd.get(0).getEmail() == "test2@test.com"

    }

    def "Get Members To Remove"() {
        when:
        def membersToRemove = googleMailingListService.getMembersToRemove(contacts, members)

        then:
        membersToRemove.size() == 1
        membersToRemove.get(0).getEmail() == "test3@test.com"
    }

}
