
import axios from 'axios';

const baseURL = 'https://faunus.fly.dev/';

const axiosInstance = axios.create({
  baseURL,
  timeout: 10000,
});

export default axiosInstance;