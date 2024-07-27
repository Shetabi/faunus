package com.faunus.api;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
@Profile("!test")
public class FirebaseConfig {


    @Value("${firebase.config}")
    private String firebaseConfigContent;

    @PostConstruct
    public void initialize() throws IOException {
        File tempFile = File.createTempFile("firebase-config", ".json");
        tempFile.deleteOnExit();

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(firebaseConfigContent.getBytes(StandardCharsets.UTF_8));
        }

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(new FileInputStream(tempFile)))
                .build();

        FirebaseApp.initializeApp(options);
    }
}
