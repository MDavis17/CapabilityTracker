package com.mdavis.capability_tracker;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

@RestController
public class CapabilityController {
    private static final String APPLICATION_NAME = "CapabilityTracker";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved credentials/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY);
//    private static final String CREDENTIALS_FILE_PATH ="/Users/mdavis17/Documents/Java/capability_tracker/src/main/resources/credentials.json";
    private static final String CREDENTIALS_FILE_PATH = "../../../../resources/credentials.json";

    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = CapabilityTrackerApplication.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();

        return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
    }

    @RequestMapping("/capabilities")
    public Capability[] getCapabilities(@RequestParam(value="versions",defaultValue = "") String[] versions,@RequestParam(value="themes",defaultValue = "") String[] themes) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();

        final String spreadsheetId = "1S4K18Z7FaBo2aKm7Tfj6WrkB2nTklR51vK8lE11hYmQ";
        final String range = "Sheet 1!A2:H31";

        Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
                .setApplicationName(APPLICATION_NAME)
                .build();
        ValueRange response = service.spreadsheets().values()
                .get(spreadsheetId, range)
                .execute();
        List<List<Object>> values = response.getValues();

        Vector<Capability> capabilities = new Vector<Capability>();

        for(List<Object> cap: values) {
            // need to set name, version, theme
            Capability cap_new = new Capability();

            // set name
            cap_new.setName(cap.get(0).toString());

            // set version number
            String version_input = "0.0.0";
            for(Object ver_field: cap.subList(1,6)) {
                if(ver_field.toString().length() != 0) {
                    version_input = ver_field.toString();
                    if (version_input.length() < 4)
                        version_input += ".0";
                    else if (version_input.length() > 5)
                        version_input = version_input.substring(0, 6);
                }
            }
            cap_new.setVersion(new Version(version_input));

            // set themes
            String[] cap_themes = cap.get(7).toString().split(",\\s");
            cap_new.setValueThemes(new Vector(Arrays.asList(cap_themes)));

            // add cap_new to list of capabilities
            capabilities.add(cap_new);
        }

        return capabilities.toArray(new Capability[capabilities.size()]);

//        return (new CapabilityCreator()).genCapabilities(versions,themes);
    }
}
