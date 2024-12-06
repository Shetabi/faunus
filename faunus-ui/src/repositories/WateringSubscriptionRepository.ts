import axiosInstance from '@/utils/api/axiosInstance';
import { Plant } from "@/types/Plant";
import { SubscriptionStatusDTO, NotificationRequest } from '@/types/SubscriptionStatus';
import retrieveToken from '@/utils/push-notification/token-service';

class WateringSubscriptionRepository {
    async fetchCurrentStatus(ownerId, plantId): Promise<SubscriptionStatusDTO> {
        try {
          const response = await axiosInstance.get(`/owners/${ownerId}/watering-notifications/${plantId}`);
          return response.data;
        } catch (error) {
          throw error;
        }
    }

    async subscribe(ownerId, plantId): Promise<SubscriptionStatusDTO> {
      try {
        const token = await retrieveToken();
        const response = await axiosInstance.post(`/owners/${ownerId}/watering-notifications/${plantId}`, {
            token: token}
        );
        return response.data[0];
      } catch (error) {
        throw error;
      }
    }

    async unSubscribe(ownerId, plantId): Promise<SubscriptionStatusDTO> {
        try {
          const response = await axiosInstance.delete(`/owners/${ownerId}/watering-notifications/${plantId}`);
          return response.data[0];
        } catch (error) {
          throw error;
        }
    }
}

export default WateringSubscriptionRepository;