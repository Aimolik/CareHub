import { CHILD_ROUTE, GUARDIAN_ROUTE } from "./ApiRoutes.js";

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

export const deleteChild = async (childId) => {
  const token = localStorage.getItem('token');
  try {
    const response = await fetch(CHILD_ROUTE + '/' + childId, {
      method: "DELETE",
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

export const getCheckedInChildren = async (guardian_id) => {
  return await getCheckedChildrenWithGuardianId(guardian_id, "checkin");
}

export const getCheckedOutChildren = async (guardian_id) => {
  return await getCheckedChildrenWithGuardianId(guardian_id, "checkout");
}

const getCheckedChildrenWithGuardianId = async (guardian_id, checking_type) => {
  const token = localStorage.getItem('token');
  try {
    const response = await fetch(GUARDIAN_ROUTE + '/' + guardian_id + '/' + checking_type, {
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
