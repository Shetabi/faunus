import { Typography } from '@material-tailwind/react';
import { Switch } from '@material-tailwind/react';
import { WateringMethod } from 'src/types/WateringMethod';
import { useState, useEffect } from 'react';
import askPermission from 'src/utils/push-notification/request-permission';

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

      const [wateringRegistered, setWateringRegistered] = useState(false);

      // Load state from local storage on component mount
      useEffect(() => {
        const savedState = localStorage.getItem('WATERING_REGISTERED');
        if (savedState !== null) {
          setWateringRegistered(JSON.parse(savedState));
        }
      }, []);


    const onToggleEvent = (e) => {
        const newState = !wateringRegistered;
        askPermission().then(() => {
            localStorage.setItem('WATERING_REGISTERED', newState);
            setWateringRegistered(JSON.parse(newState));
        }).catch((error) => {
            console.log("permission denied");
        });
    }
    return (
        <div className={`flex flex-col mb-6`}>
            <Switch onChange={onToggleEvent} color="white" checked={wateringRegistered} label={
                    <div className="mr-4 text-white">آبیاری را یادآوری کن</div>
                } />
            <Typography variant="h3" className={`text-white font-vazirmatn mt-8`} {...props}>
                        {watering?.title}
            </Typography>
            <Typography className={`text-white font-vazirmatn mt-4`} {...props}>
                {watering?.description}
            </Typography>
         </div>
    );
}

export default WateringDetails;





