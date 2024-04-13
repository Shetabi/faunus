import { Vazirmatn } from "next/font/google";
import { Typography, Chip } from '@material-tailwind/react';

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';
import { faTemperatureLow } from '@fortawesome/free-solid-svg-icons';

interface PlantChipProps {
}

library.add(faTemperatureLow);

const vazirmatn = Vazirmatn({ subsets: ["latin"] });

const handlePointerLeave = () => {
    // No operation
};

const PlantChip: React.FC<PlantChipProps> = () => {
    return (
        <div className={`flex reverse mb-6`}>
            <Chip className={`bg-accent  ${vazirmatn.className}`} value="هوای خنک" size="sm"
                icon={
                    <FontAwesomeIcon icon={faTemperatureLow} transform="shrink-1" className="text-white fa-solid"/>
            }/>
         </div>
    );
}

export default PlantChip;





