import axiosInstance from '@/utils/api/axiosInstance';
import { Plant } from "@/types/Plant";
import { SubscriptionStatusDTO, NotificationRequest } from '@/types/SubscriptionStatus';

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
        const response = await axiosInstance.post(`/owners/${ownerId}/watering-notifications/${plantId}`, {
            token: "ddzZeiNRC9djTvDSFNaNOs:APA91bGJjGfXF94wcwxus1gzHWTAgYdh5Z0UyOdMNXx-85kQ_T1dnt8SG4YK4gchlMgX5Qtmlvjox_XPZ-UDxkBqhl0C_D-QzEeHfU6jn0lRPn3j1mW8R7cfZqX63iOHQxZ0AEmmzIAb"}
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