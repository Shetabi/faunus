package com.faunus.api.core.notification;

import com.faunus.api.core.owner.OwnerPlant;
import com.faunus.api.core.owner.OwnerPlantRepository;
import com.faunus.api.core.watering.WateringEventType;
import com.faunus.api.core.watering.WateringLog;
import com.faunus.api.core.watering.WateringLogRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final OwnerPlantRepository ownerPlantRepository;
    private final WateringLogRepository wateringLogRepository;

    public void notifyOwner(Long ownerPlantId) {
        Notification notification = Notification.builder()
                .setTitle("زمان آبیاری گیاه است")
                .setBody("با توجه به دستور آبیاری گیاه خود را آبیاری کنید")
                .build();

        Optional<OwnerPlant> ownerPlant = ownerPlantRepository.findById(ownerPlantId);

        ownerPlant.ifPresent( op -> {
            List<WateringLog> events = wateringLogRepository.findByOwnerPlantId(op.getId());

            Optional<WateringLog> first = events.stream()
                    .filter(wlog -> wlog.getType().equals(WateringEventType.SUBSCRIBED))
                    .max(Comparator.comparing(WateringLog::getCreatedOn));

            first.ifPresent(lastSubscribe -> {
                Message message = Message.builder()
                        .setToken(lastSubscribe.getPayload())
                        .setNotification(notification)
                        .build();

                try {
                    String response = FirebaseMessaging.getInstance().send(message);
                     log.info("Message sent and received response is: {}", response);
                } catch (Exception e) {
                    log.error("Message sent and received response is: {}", e.getMessage());
                }
            });

        });
    }
}
