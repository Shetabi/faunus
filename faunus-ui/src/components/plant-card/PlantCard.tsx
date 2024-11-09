import { Card, CardHeader, CardBody, Typography } from '@material-tailwind/react';

import { library } from '@fortawesome/fontawesome-svg-core';
import { faTemperatureLow } from '@fortawesome/free-solid-svg-icons';
import { Plant } from "src/types/Plant";
import PlantChip from 'src/components/plant-chip/PlantChip';
import {WateringDetailsProps, WateringDetails} from 'src/components/watering-details/WateringDetails';
import "./PlantCard.css";

interface PlantCardProps {
    plant: Plant;
}

library.add(faTemperatureLow);

const handlePointerLeave = () => {
    // No operation
};

const props = {
    placeholder: 'card',
    onPointerEnterCapture: {handlePointerLeave},
    onPointerLeaveCapture: {handlePointerLeave}
}


const PlantCard: React.FC<PlantCardProps> = ({ plant }) => {
    const watering: WateringDetailsProps = {
        plantId: plant.id,
        watering: plant.watering
    }
    return (
        <Card className="card bg-primary-dark-lighter" {...props}>
            <CardHeader className="relative mt-3 h-100" {...props}>
                <img className="object-cover h-full w-full" src="https://images.unsplash.com/photo-1584589167171-541ce45f1eea?q=80&w=3087&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt="plant" />
            </CardHeader>
            <CardBody className="dir-rtl" {...props}>
                <Typography className={`text-white text-center font-semibold text-4xl m-5 font-vazirmatn`} {...props}>
                      {plant.name}
                </Typography>

                <PlantChip icon={ faTemperatureLow } text='هوای خنک'/>

                {typeof plant !== 'undefined' && typeof plant.id !== 'undefined'
                && <WateringDetails watering={plant.watering} plantId={plant.id}/>}
            </CardBody>
        </Card>
    );
}

export default PlantCard;