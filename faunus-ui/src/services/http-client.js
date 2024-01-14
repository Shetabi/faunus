import axios from 'axios';

export default function get(path) {
    if (!path.startsWith('/')) {
        path = `/${path}`;
    }
    // console.log(`${process.env.REACT_APP_BASE_URL}${path}`);
    return axios.get(`${process.env.REACT_APP_BASE_URL}${path}`);
}