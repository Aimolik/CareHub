import { getRegisteredChildrenWithGuardianId, getCheckedOutChildren } from "../service/ChildService.js";
import { getJwtTokenPayload } from '../service/AccountService.js';
import { checkinChild } from "../service/CheckingService.js";

const childSelect = document.getElementById('child-select');
const submitButton = document.getElementById('checkin-button');
const payload = getJwtTokenPayload();

const token = localStorage.getItem('token');
console.log('Token:', token);

const populateChildDropdown = async () => {
    try {
        //const response = await getRegisteredChildrenWithGuardianId(payload.accountId);
        const response = await getCheckedOutChildren(payload.accountId);
        console.log(response);
        for (let i = 0; i < response.length; i++) {
            const childData = typeof response[i] === 'string' ? JSON.parse(response[i]) : response[i];
            const option = document.createElement('option');
            option.value = childData.childId; 
            option.text = childData.name;
            childSelect.appendChild(option);
        }
    } catch (error) {
        console.error('Error fetching or parsing data:', error);
        alert('Failed to fetch child data. Please try again.');
    }
}

populateChildDropdown();

async function getChildStatus(childId) {
    const response = await fetch(`http://localhost:8080/api/child/status/${childId}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        }
    });

    if (!response.ok) {
        throw new Error (`HTTP Error ${response.status}`);
    }

    return await response.json(); 
}

submitButton.addEventListener('click', async (e) => {
    e.preventDefault();

    const childId = childSelect.value;
    const checkInTime = new Date().toISOString();

    // const alreadyCheckedIn = Array.from(childSelect.options).some(option => option.value === childId);
    // if (alreadyCheckedIn) {
    //     alert('Child has already been checked in.');
    //     return;
    // }

    try {
        const result = await checkinChild(childId, checkInTime);
        console.log(result);
        
       if(result && result.statusCode == 200) {
            alert('Child checked in successfully!');
            childSelect.remove(childSelect.selectedIndex);
        } else {
            alert('Failed to check in child. Please try again.');
        }
    } catch (e) {
        console.error('Error during check-in:', e);
        alert('Failed to check in child. Please try again.');
    }
});
