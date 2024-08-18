import { initializeApp } from 'firebase/app';

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyBkn1rhH5G5o6CsyWlfuBKTDUkl6HkAHlM",
  authDomain: "faunus-4a27c.firebaseapp.com",
  projectId: "faunus-4a27c",
  storageBucket: "faunus-4a27c.appspot.com",
  messagingSenderId: "142280280684",
  appId: "1:142280280684:web:52cf9f489b2be68e452649",
  measurementId: "G-VTX9RBKVHK"
};

// Initialize Firebase
const firebaseApp = initializeApp(firebaseConfig);

export default firebaseApp;