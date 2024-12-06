import { Typography } from '@material-tailwind/react';
import { Switch } from '@material-tailwind/react';
import { WateringMethod } from 'src/types/WateringMethod';
import { WateringSubscriptionStatus } from 'src/types/SubscriptionStatus';
import { useState, useEffect } from 'react';
import askPermission from 'src/utils/push-notification/request-permission';
import WateringSubscriptionRepository from 'src/repositories/WateringSubscriptionRepository';

interface WateringDetailsProps {
    watering: WateringMethod,
    plantId: number;
}

const repository = new WateringSubscriptionRepository();

const WateringDetails: React.FC<WateringDetailsProps> = ( { watering, plantId} ) => {

      const [wateringRegistered, setWateringRegistered] = useState(false);

      useEffect(() => {
          const ownerId = localStorage.getItem('ownerId');
          repository.fetchCurrentStatus(ownerId, plantId)
          .then(response => {
                if (response.status === WateringSubscriptionStatus.SUBSCRIBED) {
                    setWateringRegistered(true);
                } else {
                    setWateringRegistered(false);
                }
          }).catch(err => {
                 console.log(JSON.stringify(err));
              }
          );
      });


    const onToggleEvent = (e) => {

        askPermission().then(() => {
            const ownerId = localStorage.getItem('ownerId');
            askPermission();
            if (!ownerId || !plantId) {
                return;
            }
            if (!wateringRegistered) {
                repository.subscribe(ownerId, plantId);
                setWateringRegistered(true);
            } else {
                repository.unSubscribe(ownerId, plantId);
                setWateringRegistered(false);
            }
        }).catch((error) => {
            console.log(error);
        });
    }
    return (
        <div className={`flex flex-col mb-6`}>
            <Switch onChange={onToggleEvent} color="white" checked={wateringRegistered} label={
                    <div className="mr-4 text-white">آبیاری را یادآوری کن</div>
                } />
            <Typography variant="h3" className={`text-white font-vazirmatn mt-8`}>
                        {watering?.title}
            </Typography>
            <Typography className={`text-white font-vazirmatn mt-4`}>
                {watering?.description}
            </Typography>
         </div>
    );
}

export {WateringDetails, WateringDetailsProps};





