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
              vapidKey: process.env.NEXT_PUBLIC_FIREBASE_API_KEY,
            });
            if (currentToken) {
              return currentToken;
            } else {
              throw new Error('Permission not granted.')
            }
          }
        }
      } catch (error) {
        throw new Error('Failed to fetch token.');
      }
};

export default retrieveToken;