import { Card, CardHeader, CardBody, Typography, Chip } from '@/components/material';

import { Vazirmatn } from "next/font/google";

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faTemperatureLow } from '@fortawesome/free-solid-svg-icons';
import { Plant } from "@/types/Plant";

library.add(faTemperatureLow);

const vazirmatn = Vazirmatn({ subsets: ["latin"] });

export default function PlantCard({ plant }) {
    return (
        <Card className="w-96 bg-primary-dark flex flex-col">
            <CardHeader className="relative mt-6 h-56">
                <img className="object-cover h-full w-full" src="https://images.unsplash.com/photo-1584589167171-541ce45f1eea?q=80&w=3087&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt="plant" />
            </CardHeader>
            <CardBody className="dir-rtl">
                <Typography className={`text-white text-center font-semibold text-4xl m-5 ${vazirmatn.className}`}>
                      {plant.name}
                </Typography>
                <div className={`flex mb-6`}>
                    <Chip className={`bg-accent  ${vazirmatn.className}`} value="هوای خنک" size="sm"
                        icon={
                            <FontAwesomeIcon icon="h-10 fa-solid fa-temperature-low" transform="shrink-6" className="text-white"/>
                    }/>
                 </div>

                <Typography className={`text-white ${vazirmatn.className}`}>
                  چگونه گیاه را آبیاری کنیم. اینجا توضیح میدهیم چه گامهایی باید برداشت تا گیاه را آبیاری کرد.
                </Typography>
            </CardBody>
        </Card>
    );
}