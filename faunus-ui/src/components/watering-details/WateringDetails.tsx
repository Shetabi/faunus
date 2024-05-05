import { Typography } from '@material-tailwind/react';
import { WateringMethod } from 'src/types/WateringMethod';

interface WateringDetailsProps {
    watering: WateringMethod
}


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
            <Typography variant="h4" className={`mb-4 text-white font-vazirmatn`} {...props}>
                        {watering?.title}
            </Typography>
            <Typography className={`text-white font-vazirmatn`} {...props}>
                {watering?.description}
            </Typography>
         </div>
    );
}

export default WateringDetails;





