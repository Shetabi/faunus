import axiosInstance from '@/utils/api/axiosInstance';
import { Owner } from "@/types/Owner";

class OwnerRepository {
  async ownerFetch(ownerName): Promise<Owner> {
    try {
      const response = await axiosInstance.get(`/owners/${ownerName}`);
      return response.data[0];
    } catch (error) {
      console.error('Error fetching data:', error);
      throw error;
    }
  }
}

export default OwnerRepository;