import { CHILD_ROUTE, GUARDIAN_ROUTE } from "../ApiRoutes.js";

export const registerChild = async (body) => {
  const token = localStorage.getItem('token');
  try {
    const response = await fetch(CHILD_ROUTE, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
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

export const getRegisteredChildrenWithGuardianId = async (guardian_id) => {
  const token = localStorage.getItem('token');
  try {
    const response = await fetch(GUARDIAN_ROUTE + '/' + guardian_id + '/children', {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        "Authorization": "Bearer " + token
      }
    });
    const message = await response.json();
    return message;
  } catch (e) {
    console.log(e);
    return null;
  }
}