package no.fint.customer.mailinglist.provisioning.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.admin.directory.Directory;
import com.google.api.services.admin.directory.DirectoryScopes;
import no.fint.customer.mailinglist.provisioning.Props;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.List;

@Service
public class GoogleService {

    private static final List<String> SCOPES = Arrays.asList(DirectoryScopes.ADMIN_DIRECTORY_GROUP, DirectoryScopes.ADMIN_DIRECTORY_GROUP_MEMBER);


    @Autowired
    private Props props;

    private Credential getCredentials() throws IOException {

        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();

        FileInputStream stream = new FileInputStream(props.getCredentialsFilePath());
        GoogleCredential googleCredential = GoogleCredential
                .fromStream(stream, httpTransport, jsonFactory)
                .createScoped(SCOPES);

        return new GoogleCredential.Builder()
                .setTransport(googleCredential.getTransport())
                .setJsonFactory(googleCredential.getJsonFactory())
                .setServiceAccountId(googleCredential.getServiceAccountId())
                .setServiceAccountUser(props.getServiceAccountUser())
                .setServiceAccountPrivateKey(googleCredential.getServiceAccountPrivateKey())
                .setServiceAccountScopes(googleCredential.getServiceAccountScopes())
                .build();

    }

    public Directory getGoogleService() throws GeneralSecurityException, IOException {
        final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        return new Directory
                .Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials())
                .setApplicationName(props.getApplicationName())
                .build();
    }
}
