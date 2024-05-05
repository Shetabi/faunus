import { WateringMethod } from '@/src/types/WateringMethod';
export interface Plant {
    name: string;
    id: number;
    watering: WateringMethod;
}