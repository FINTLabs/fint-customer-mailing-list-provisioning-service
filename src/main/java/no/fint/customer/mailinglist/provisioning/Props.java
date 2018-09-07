package no.fint.customer.mailinglist.provisioning;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Props {

    @Value("${fint.customer.mailinglist.provisioning.credentials:creds.p12}")
    private String credentialsFilePath;

    @Value("${fint.customer.mailinglist.provisioning.app-name:FINT customer mailing list provisioning}")
    private String applicationName;

    @Value("${fint.customer.mailinglist.provisioning.service-account-email}")
    private String serviceAccountEmail;

    @Value("${fint.customer.mailinglist.provisioning.service-account-user}")
    private String serviceAccountUser;

    @Value("${fint.customer.mailinglist.provisioning.mailing-list}")
    private String mailingList;

    @Value("${fint.customer.mailinglist.provisioning.interval:50000}")
    private long provisioningInterval;

}
