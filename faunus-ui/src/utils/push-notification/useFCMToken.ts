'use client'
import { useEffect, useState } from 'react';
import { getMessaging, getToken } from 'firebase/messaging';
import firebaseApp from 'src/utils/push-notification/firebase';

const useFcmToken = () => {
  const [token, setToken] = useState('');
  const [notificationPermissionStatus, setNotificationPermissionStatus] = useState('');

  useEffect(() => {
    const retrieveToken = async () => {
      try {
        if (typeof window !== 'undefined' && 'serviceWorker' in navigator) {
          const messaging = getMessaging(firebaseApp);

          // Request notification permission
          const permission = await Notification.requestPermission();
          setNotificationPermissionStatus(permission);

          if (permission === 'granted') {
            const currentToken = await getToken(messaging, {
              vapidKey: 'BI6eNnjVUAGUs1AGSW4TgWWOIK8KopTy81ttiJ5P1c63ConsK9vdICATR7s_MJB44n-io-DjeaazZeOAUNdoVKs', // Replace with your Firebase project's VAPID key
            });
            if (currentToken) {
                console.log("token: " + currentToken);
              setToken(currentToken);
            } else {
              console.log('No registration token available. Request permission to generate one.');
            }
          }
        }
      } catch (error) {
        console.log('Error retrieving token:', error);
      }
    };

    retrieveToken();
  }, []);

  return { token, notificationPermissionStatus };
};

export default useFcmToken;