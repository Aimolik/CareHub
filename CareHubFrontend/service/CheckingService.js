import { CHECKING_ROUTE } from '../ApiRoutes.js';

export async function checkinChild(childId, checkInTime) {
    const token = localStorage.getItem('token');
    // const body = { childId, checkInTime };
    const body = { childId, checkingType: "CHECK_IN" };

    try {
        const response = await fetch(CHECKING_ROUTE, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            },
            body: JSON.stringify(body)
        });

        if (!response.ok) {
            throw new Error (`HTTP Error ${response.status}`);
        }

        return await response.json(); 
    } catch (e) {
        console.error('Error communicating with server:', e);
        throw e;
    }
}