import { getRegisteredChildrenWithGuardianId } from "./service/ChildService.js";
import { getJwtTokenPayload } from './service/AccountService.js';

const container = document.querySelector('.child-container');

const getChildren = async () => {
  const payload = getJwtTokenPayload();
  const response = await getRegisteredChildrenWithGuardianId(payload.accountId);

  for(let i = 0; i < response.length; i++) {
    const child = response[i];

    const li = document.createElement('li');
    li.className = 'child-item'; 

    const nameDiv = document.createElement('div');
    nameDiv.className = 'child-info';
    nameDiv.innerHTML = '<span class="child-label">Name:</span> ' + child.name;

    const dobDiv = document.createElement('div');
    dobDiv.className = 'child-info';
    dobDiv.innerHTML = '<span class="child-label">Date of Birth:</span> ' + child.dateOfBirth; 

    const addressDiv = document.createElement('div'); 
    addressDiv.className = 'child-info';
    addressDiv.innerHTML = '<span class="child-label">Address:</span> ' + child.address; 

    const medicalDiv = document.createElement('div'); 
    medicalDiv.className = 'child-info';
    medicalDiv.innerHTML = '<span class="child-label">Medical Information:</span> ' + child.medicalInformation; 

    li.appendChild(nameDiv);
    li.appendChild(dobDiv);
    li.appendChild(addressDiv);
    li.appendChild(medicalDiv);

    container.appendChild(li);
  }
}

getChildren();
