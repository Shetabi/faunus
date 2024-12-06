
import axios from 'axios';

const baseURL = process.env.NEXT_PUBLIC_BASE_URL;

const axiosInstance = axios.create({
  baseURL,
  timeout: 10000,
});

export default axiosInstance;