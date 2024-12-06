'use client'
import { useEffect, useState } from 'react';
import { getMessaging, getToken } from 'firebase/messaging';
import firebaseApp from 'src/utils/push-notification/firebase';

const retrieveToken = async () => {
      try {
        if (typeof window !== 'undefined' && 'serviceWorker' in navigator) {
          const messaging = getMessaging(firebaseApp);

          // Request notification permission
          const permission = await Notification.permission;

          if (permission === 'granted') {
            const currentToken = await getToken(messaging, {
              vapidKey: process.env.NEXT_PUBLIC_VAPIDKEY,
            });
            if (currentToken) {
                console.log('token: ', currentToken);
              return currentToken;
            } else {
              throw new Error('Client token is empty.')
            }
          }
        }
      } catch (error) {
        throw new Error(JSON.stringify(error));
      }
};

export default retrieveToken;