"use client";

import React, { useState, useEffect } from 'react';

import Image from "next/image";
import { Button } from "src/components/material";
import PlantCard from 'src/components/plant-card/PlantCard';
import { Vazirmatn } from "next/font/google";
import { Plant } from "src/types/Plant";
import PlantRepository from 'src/repositories/PlantRepository';

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
        <main>
            <PlantCard plant={ plant }/>
        </main>
      );
    }
