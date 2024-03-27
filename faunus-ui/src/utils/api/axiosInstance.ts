
import axios from 'axios';

const baseURL = process.env.BASE_URL || 'https://faunus.fly.dev';

const axiosInstance = axios.create({
  baseURL,
  timeout: 10000,
});

export default axiosInstance;