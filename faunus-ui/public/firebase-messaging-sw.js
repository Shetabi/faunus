importScripts('https://www.gstatic.com/firebasejs/10.5.0/firebase-app-compat.js');
importScripts('https://www.gstatic.com/firebasejs/10.5.0/firebase-messaging-compat.js');

// Fetch Firebase config from API
self.addEventListener('install', () => {
  self.skipWaiting(); // Force the waiting service worker to become the active one
});

self.addEventListener('activate', () => {
  clients.claim(); // Claim clients so it's active immediately
});

async function initializeFirebase() {
  const configResponse = await fetch('/api/firebase-config');
  console.log('config received ', configResponse);
  const firebaseConfig = await configResponse.json();

  console.log('background config', firebaseConfig);

  // Initialize Firebase with the dynamically fetched config
  firebase.initializeApp(firebaseConfig);

  const messaging = firebase.messaging();

  messaging.onBackgroundMessage((payload) => {
    console.log('[firebase-messaging-sw.js] Received background message ', payload);

    const { title, body, image, icon, ...restPayload } = payload.notification || {};
    const notificationTitle = title || 'New Notification';
    const notificationBody = body || 'You have received a new message.';
    const notificationOptions = {
      body: notificationBody,
      icon: icon || '/icons/apple-touch-icon.png',
      data: restPayload || {},
      image: image || undefined,
    };

      // Handle `data`-only messages
      if (!payload.notification) {
        const { title: dataTitle, body: dataBody, ...data } = payload.data || {};
        notificationTitle = dataTitle || 'New Notification';
        notificationBody = dataBody || 'You have received a new message.';
        notificationOptions.data = data;
      }

    return self.registration.showNotification(notificationTitle, notificationOptions);
  });
}

initializeFirebase();
