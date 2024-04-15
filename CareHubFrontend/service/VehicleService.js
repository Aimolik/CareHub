import { VEHICLE_ROUTE } from "./ApiRoutes.js";

export const registerVehicle = async (body) => {
    const token = localStorage.getItem('token');
    try {
        const response = await fetch(VEHICLE_ROUTE, {
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