'use client'
import { getMessaging, onMessage } from 'firebase/messaging';
import firebaseApp from 'src/utils/push-notification/firebase';
import { useEffect } from 'react';
import WateringSubscriptionRepository from 'src/repositories/WateringSubscriptionRepository';

const repository = new WateringSubscriptionRepository();

export default function NotificationListener() {

  useEffect(() => {
    if (typeof window !== 'undefined' && 'serviceWorker' in navigator) {
        const messaging = getMessaging(firebaseApp);
        const unsubscribe = onMessage(messaging, (payload) => {
        console.log('Received foreground message: ', payload);
          const notification = payload.notification;
          if (notification && Notification.permission === 'granted') {
            const notificationTitle = notification.title || 'New Notification';
            const notificationOptions = {
              body: notification.body || 'You have a new message.',
              icon: notification.icon || '/icons/apple-touch-icon.png',
              // Additional options like click actions, vibration, etc.
            };

            new Notification(notificationTitle, notificationOptions);
          } else {
            console.log('Permission not granted');
          }
        });

        return () => {
          unsubscribe();
        };
    }
  }, []);

  return null; // This component is primarily for handling foreground notifications
}