import axios from "axios";
import config from "./config";

axios.defaults.baseURL = config.apiUrl;

export const generateShortUrl = obj => {
  const requestUrl = "urls";
  return axios.post(requestUrl, obj);
};

export const followShortUrl = id => {
  const requestUrl = "urls/" + id;
  return axios.get(requestUrl, { params: { click: true } });
};
