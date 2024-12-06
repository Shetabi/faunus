import axiosInstance from '@/utils/api/axiosInstance';

class LoginService {
  async login(ownerName): Promise<number> {
    try {
      const response = await axiosInstance.post(`/login`, {
            userName: ownerName
          });
      return response.data.ownerId;

    } catch (error) {
      console.error('Error fetching data:', error);
      throw error;
    }
  }
}

export default LoginService;