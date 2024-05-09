import { Typography, Chip } from '@material-tailwind/react';
import { IconDefinition } from "@fortawesome/fontawesome-common-types";


import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { library } from '@fortawesome/fontawesome-svg-core';

interface PlantChipProps {
    text: string
    icon: IconDefinition
}


const handlePointerLeave = () => {
    // No operation
};

const PlantChip: React.FC<PlantChipProps> = ( { text, icon } ) => {
    return (
        <div className={`flex reverse mb-6`}>
            <Chip data-testid="chip-component" className={`bg-accent font-vazirmatn`} value={ text } size="sm"
                icon={
                    <FontAwesomeIcon icon={icon} transform="shrink-1" className="text-white fa-solid"/>
            }/>
         </div>
    );
}

export default PlantChip;





