import { getRegisteredChildrenWithGuardianId } from "./service/ChildService.js";
import { getJwtTokenPayload } from './service/AccountService.js';

const childSelect = document.getElementById('child-select');
const submitButton = document.getElementById('checkout-button');
const payload = getJwtTokenPayload();

const token = localStorage.getItem('token');
console.log('Token:', token);

const populateChildDropdown = async () => {
    try {
        const response = await getRegisteredChildrenWithGuardianId(payload.accountId);

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

submitButton.addEventListener('click', async (e) => {
    e.preventDefault();

    const childId = childSelect.value;
    const checkOutTime = new Date().toISOString();

    try {
        const result = await registerChild(childId, checkOutTime);

        if(result && result.statusCode == 200) {
            alert('Child checked out successfully!');
            setChildCheckoutStatus(childId, 'checked_out');  // set local status
            childSelect.remove(childSelect.selectedIndex);
        } else {
            alert('Failed to check out child. Please try again.');
        }
    } catch (e) {
        console.error('Error during check-out:', e);
        alert('Failed to check out child. Please try again.');
    }
});

async function registerChild(childId, checkOutTime) {
    const token = localStorage.getItem('token');
    const body = { childId, checkingType: 'CHECK_OUT', checkOutTime }; 
    try {
        const response = await fetch('http://localhost:8080/api/checking', {
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

function setChildCheckoutStatus(childId, status) {
    localStorage.setItem('checkoutStatus_' + childId, status);
}
