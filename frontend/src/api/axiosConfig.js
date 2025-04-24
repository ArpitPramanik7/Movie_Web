// import axios from 'axios';

// const api = axios.create({
//   baseURL: 'http://7dac-202-91-134-147.ngrok-free.app',
//   headers: {
//     "ngrok-skip-browser-warning": "true",
//     "Content-Type": "application/json"
//   },
//   withCredentials: true  // Add this if you're using credentials/cookies
// });

// export default api;

// // api_endpoints which are called by our client side
// // specific endpoints are called ngrok
// // ngrok are used to overcome CORS

import axios from "axios";

const api = axios.create({
  baseURL: "http://localhost:8080",
});

// Automatically attach token to each request
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

export default api;



