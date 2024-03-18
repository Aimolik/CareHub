import { GUARDIAN_ROUTE, STAFF_ROUTE, AUTHENTICATE_ROUTE } from "../ApiRoutes.js";

export const registerAccount = async (body, accountType) => {
  const route = accountType === "GUARDIAN" ? GUARDIAN_ROUTE : STAFF_ROUTE;
  try {
    const response = await fetch(route, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(body)
    });
    const message = await response.json();
    return message;
  } catch (e) {
    console.log(e);
    return null;
  }
}

export const login = async (body) => {
  try {
    const response = await fetch(AUTHENTICATE_ROUTE, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(body)
    });
    const message = await response.json();
    return message;
  } catch (e) {
    console.log(e);
    return null;
  }  
}

export const getJwtTokenPayload = (token) => {
  const base64Url = token.split('.')[1];
  const base64 = base64Url.replace(/-/g, '+').replace(/_/g, '/');
  const jsonPayload = decodeURIComponent(atob(base64).split('').map(function(c) {
    return '%' + ('00' + c.charCodeAt(0).toString(16)).slice(-2);
  }).join(''));
  return JSON.parse(jsonPayload);
}