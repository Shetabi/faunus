import axiosInstance from '@/utils/api/axiosInstance';
import { Plant } from "@/types/Plant";

class PlantRepository {
  async fetchPlant(ownerId): Promise<Plant> {
    try {
      const response = await axiosInstance.get(`/plants?ownerId=${ownerId}`);
      return response.data[0];
    } catch (error) {
      console.error('Error fetching data:', error);
      throw error;
    }
  }
}

export default PlantRepository;