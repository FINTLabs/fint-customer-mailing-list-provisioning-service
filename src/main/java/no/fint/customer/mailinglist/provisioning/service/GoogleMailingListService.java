package no.fint.customer.mailinglist.provisioning.service;

import com.google.api.services.admin.directory.Directory;
import com.google.api.services.admin.directory.model.Member;
import lombok.extern.slf4j.Slf4j;
import no.fint.customer.mailinglist.provisioning.Props;
import no.fint.portal.model.contact.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GoogleMailingListService {


    @Autowired
    private GoogleService googleService;
    @Autowired
    private Props props;

    public void updateMailingList(List<Contact> contacts) throws GeneralSecurityException, IOException {

        Directory service = googleService.getGoogleService();

        List<Member> members = service.members().list(props.getMailingList()).execute().getMembers();
        List<Member> deleteMembers = getMembersToRemove(contacts, members);
        List<Member> newMembers = getMembersToAdd(contacts, members);

        log.info("  {} members to add.", newMembers.size());
        log.info("  {} members to remove.", deleteMembers.size());

        for (Member deleteMember : deleteMembers) {
            service.members().delete(props.getMailingList(), deleteMember.getId()).execute();
        }
        for (Member newMember : newMembers) {
            service.members().insert(props.getMailingList(), newMember).execute();
        }


    }

    private List<Member> getMembersToAdd(List<Contact> contacts, List<Member> members) {
        List<Member> newMembers = new ArrayList<>();
        contacts.forEach(contact -> {
            if (members != null) {
                if (members.stream().noneMatch(member -> member.getEmail().toLowerCase().equals(contact.getMail().toLowerCase()))) {
                    Member member = new Member();
                    member.setEmail(contact.getMail().toLowerCase());
                    newMembers.add(member);
                }
            } else {
                Member member = new Member();
                member.setEmail(contact.getMail().toLowerCase());
                newMembers.add(member);
            }
        });

        return newMembers;
    }

    private List<Member> getMembersToRemove(List<Contact> contacts, List<Member> members) {
        List<Member> deleteMembers = new ArrayList<>();
        if (members != null) {
            members.forEach(member -> {
                if (contacts.stream().noneMatch(contact -> contact.getMail().toLowerCase().equals(member.getEmail().toLowerCase()))) {
                    deleteMembers.add(member);
                }
            });
        }

        return deleteMembers;
    }

}
