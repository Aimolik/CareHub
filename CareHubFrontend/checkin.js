import { getRegisteredChildrenWithGuardianId } from "./service/ChildService.js";
import { getJwtTokenPayload } from './service/AccountService.js';

const childSelect = document.getElementById('child-select');
const payload = getJwtTokenPayload();

const populateChildDropdown = async () => {
    try {
        const response = await getRegisteredChildrenWithGuardianId(payload.accountId);

        for (let i = 0; i < response.length; i++) {
            const childData = typeof response[i] === 'string' ? JSON.parse(response[i]) : response[i];
            const option = document.createElement('option');
            option.value = childData.childId; // Assuming childId is correct
            option.text = childData.name;
            childSelect.appendChild(option);
        }
    } catch (error) {
        console.error('Error fetching or parsing data:', error);
    }
}

populateChildDropdown();
