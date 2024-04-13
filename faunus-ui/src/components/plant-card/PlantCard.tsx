import { Vazirmatn } from "next/font/google";
import { Card, CardHeader, CardBody, Typography, Chip } from '@material-tailwind/react';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faTemperatureLow } from '@fortawesome/free-solid-svg-icons';
import { Plant } from "src/types/Plant";
import "./PlantCard.css";

interface PlantCardProps {
    plant: Plant;
}

library.add(faTemperatureLow);

const vazirmatn = Vazirmatn({ subsets: ["latin"] });

const handlePointerLeave = () => {
    // No operation
};

const props = {
    placeholder: 'card',
    onPointerEnterCapture: {handlePointerLeave},
    onPointerLeaveCapture: {handlePointerLeave}
}

const PlantCard: React.FC<PlantCardProps> = ({ plant }) => {
    return (
        <Card className="card bg-primary-dark-lighter" {...props}>
            <CardHeader className="relative mt-3 h-100" {...props}>
                <img className="object-cover h-full w-full" src="https://images.unsplash.com/photo-1584589167171-541ce45f1eea?q=80&w=3087&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt="plant" />
            </CardHeader>
            <CardBody className="dir-rtl" {...props}>
                <Typography className={`text-white text-center font-semibold text-4xl m-5 ${vazirmatn.className}`} {...props}>
                      {plant.name}
                </Typography>
                <div className={`flex mb-6`}>
                    <Chip className={`bg-accent  ${vazirmatn.className}`} value="هوای خنک" size="sm"
                        icon={
                            <FontAwesomeIcon icon={faTemperatureLow}   transform="shrink-6" className="text-white h-10 fa-solid"/>
                    }/>
                 </div>

                <Typography className={`text-white ${vazirmatn.className}`} {...props}>
                  چگونه گیاه را آبیاری کنیم. اینجا توضیح میدهیم چه گامهایی باید برداشت تا گیاه را آبیاری کرد.
                </Typography>
            </CardBody>
        </Card>
    );
}

export default PlantCard;