"use client";

import React, { useState, useEffect } from 'react';
import PlantRepository from '@/repositories/PlantRepository';

import Image from "next/image";
import {Button} from "@/components/material";
import PlantCard from '@/components/PlantCard';
import { Vazirmatn } from "next/font/google";
import { Plant } from "@/types/Plant";

const vazirmatn = Vazirmatn({ subsets: ["latin"] });

export default function Home() {
    const [plant, setPlant] = useState<Plant>({ id: 0, name: '' });

    useEffect(() => {
        const fetchData = async () => {
          try {
            const repository = new PlantRepository();

            const plant = await repository.fetchPlant();
            setPlant(plant);
          } catch (error) {
            console.error('Error fetching data:', error);
            setPlant({ id: -1, name: '' });
          }
        };

        fetchData();
    }, []);
  return (
    <main className="flex min-h-screen flex-col items-center justify-between p-24">
        <PlantCard plant={ plant }/>
    </main>
  );
}
