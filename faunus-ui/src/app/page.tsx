"use client";

import React, { useState, useEffect } from 'react';

import Image from "next/image";
import { Button } from "src/components/material";
import PlantCard from 'src/components/plant-card/PlantCard';
import { Vazirmatn } from "next/font/google";
import { Plant } from "src/types/Plant";
import PlantRepository from 'src/repositories/PlantRepository';
import firebase from 'firebase/app';
import 'firebase/messaging';
import FCMTokenComp from 'src/components/push-notification/FCMTokenComp';
import withAuth from 'src/components/auth/withAuth';

// const firebaseConfig = {
//   apiKey: "AIzaSyBkn1rhH5G5o6CsyWlfuBKTDUkl6HkAHlM",
//   authDomain: "faunus-4a27c.firebaseapp.com",
//   projectId: "faunus-4a27c",
//   storageBucket: "faunus-4a27c.appspot.com",
//   messagingSenderId: "142280280684",
//   appId: "1:142280280684:web:52cf9f489b2be68e452649",
//   measurementId: "G-VTX9RBKVHK"
// };

// if (!firebase.apps.length) {
//   firebase.initializeApp(firebaseConfig);
// } else {
//   firebase.app();
// }
//
// const messaging = firebase.messaging();


const vazirmatn = Vazirmatn({
    subsets: ['latin'],
    variable: '--font-vazirmatn',
    }
);

function  Home() {
    const [plant, setPlant] = useState<Plant>({ id: 0, name: '', watering: undefined });

    useEffect(() => {
        const fetchData = async () => {
            try {
                const repository = new PlantRepository();
                const ownerId = localStorage.getItem('ownerId');
                const plant = await repository.fetchPlant(ownerId);
                setPlant(plant);
            } catch (error) {
                console.error('Error fetching data:', error);
                setPlant({ id: -1, name: '', watering: undefined });
            }
        };
        fetchData();
    }, []);
        return (
        <main className={vazirmatn.variable}>
            <FCMTokenComp />
            <PlantCard plant={ plant }/>
        </main>
      );
    }
export default withAuth(Home);
