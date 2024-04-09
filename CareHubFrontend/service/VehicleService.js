import { GUARDIAN_ROUTE } from "../ApiRoutes.js";

const BASE_URL = 'http://20.55.39.9:8080';

export const registerGuardianVehicle = async (body, guardian_id) => {
  if (!guardian_id) {
    throw new Error("guardian_id is undefined or not provided");
  }

  const token = localStorage.getItem('token');
  try {
    const formedURL = `${BASE_URL}${GUARDIAN_ROUTE}/${guardian_id}/vehicles`;
    const response = await fetch(formedURL, {
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
