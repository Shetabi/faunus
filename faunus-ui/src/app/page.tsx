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
import NotificationListener from 'src/components/push-notification/NotificationListener';
import withAuth from 'src/components/auth/withAuth';

const vazirmatn = Vazirmatn({
    subsets: ['latin'],
    variable: '--font-vazirmatn',
    }
);
const repository = new PlantRepository();

function  Home() {
    const [plant, setPlant] = useState<Plant>({ });

    useEffect(() => {
        const fetchData = async () => {
            try {
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
            <NotificationListener />
            <PlantCard plant={ plant }/>
        </main>
      );
    }
export default withAuth(Home);
