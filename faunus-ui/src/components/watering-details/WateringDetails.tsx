import { Vazirmatn } from "next/font/google";
import { Typography } from '@material-tailwind/react';
import { WateringMethod } from '@/src/types/WateringMethod';

interface WateringDetailsProps {
    watering: WateringMethod
}


const vazirmatn = Vazirmatn({ subsets: ["latin"] });

const handlePointerLeave = () => {
    // No operation
};

const props = {
    placeholder: 'watering',
    onPointerEnterCapture: {handlePointerLeave},
    onPointerLeaveCapture: {handlePointerLeave}
}

const WateringDetails: React.FC<WateringDetailsProps> = ( { watering } ) => {
    return (
        <div className={`flex flex-col mb-6`}>
             <Typography variant="h4" className={`mb-4 text-white ${vazirmatn.className}`} {...props}>
                        {watering?.title}
                    </Typography>
            <Typography className={`text-white ${vazirmatn.className}`} {...props}>
                {watering?.description}
            </Typography>
         </div>
    );
}

export default WateringDetails;





