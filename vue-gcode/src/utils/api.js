import axios from 'axios'
import Qs from 'qs'
import router from "@/router";
// import {Message} from 'element-ui';
// import router from '../router'
// import {mymessage} from '@/utils/mymessage';

// axios.interceptors.response.use(success => {
//   if (success.status && success.status == 200 && success.data.status == 500) {
//     Message.error({message: success.data.msg})
//     return;
//   }
//   if (success.data.msg) {
//     Message.success({message: success.data.msg})
//   }
//   return success.data;
// }, error => {
//   if (error.response.status == 504 || error.response.status == 404) {
//     Message.error({message: '服务器被吃了( ╯□╰ )'})
//   } else if (error.response.status == 403) {
//     Message.error({message: '权限不足，请联系管理员'})
//   } else if (error.response.status == 401) {
//     mymessage.error({message: error.response.data.msg ? error.response.data.msg : '尚未登录，请登录'})
//     router.replace('/');
//   } else {
//     if (error.response.data.msg) {
//       Message.error({message: error.response.data.msg})
//     } else {
//       Message.error({message: '未知错误!'})
//     }
//   }
//   return;
// })

let base = 'http://0.0.0.0:8081';

export const postKeyValueRequest = (url, params) => {
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: params,
    transformRequest: [function (data) {
      let ret = '';
      for (let i in data) {
        ret += encodeURIComponent(i) + '=' + encodeURIComponent(data[i]) + '&'
      }
      return ret;
    }],
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded'
    }
  });
};
export const postRequest = (url, params) => {
  window.console.log(params);
  return axios({
    method: 'post',
    url: `${base}${url}`,
    data: Qs.stringify(params),
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    }
  });
};
export const putRequest = (url, params) => {
  return axios({
    method: 'put',
    url: `${base}${url}`,
    data: params,
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
      // 'Accept': 'application/json'
    }
  });
};
export const getRequest = (url, params) => {
  return axios({
    method: 'get',
    url: `${base}${url}`,
    params: params,
    headers:{
      'token': window.sessionStorage.getItem("token"),
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    }
  });
};
export const deleteRequest = (url, params) => {
  return axios({
    method: 'delete',
    url: `${base}${url}`,
    params: params
  });
};

// axios.interceptors.request.use(
//   function(config){
//     if(!window.sessionStorage.getItem('token')){
//       router.replace({
//         path: '/login',
//         query: {redirect:router.currentRoute.fullPath}
//       })
//     }else {
//       window.console.log(window.sessionStorage.getItem('token'))
//       config.headers.Authorization = window.sessionStorage.getItem('token')
//     }
//     return config;
//   },function(error){
//     return Promise.reject(error);
//   }
// )
axios.interceptors.response.use(
  function (response) {
    return response
  },
  function (error) {
    if (error.response) {
        sessionStorage.removeItem('token')
        router.replace({
          href: '/login',
          // query: {redirect: router.currentRoute.fullPath}
        })

        return Promise.reject(error.response.data);// 返回接口返回的错误信息

    }
  })
