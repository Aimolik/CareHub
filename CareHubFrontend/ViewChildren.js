import { getRegisteredChildrenWithGuardianId, deleteChild } from "../service/ChildService.js";
import { getJwtTokenPayload } from '../service/AccountService.js';

const container = document.querySelector('.child-container');

const getChildren = async () => {
  try {
    const payload = getJwtTokenPayload();
    const response = await getRegisteredChildrenWithGuardianId(payload.accountId);

    for (const child of response) {
      const li = document.createElement('li');
      li.className = 'child-item'; 

      const nameDiv = document.createElement('div');
      nameDiv.className = 'child-info';
      nameDiv.innerHTML = `<span class="child-label">Name:</span> ${child.name}`;

      const dobDiv = document.createElement('div');
      dobDiv.className = 'child-info';
      dobDiv.innerHTML = `<span class="child-label">Date of Birth:</span> ${child.dateOfBirth}`; 

      const addressDiv = document.createElement('div'); 
      addressDiv.className = 'child-info';
      addressDiv.innerHTML = `<span class="child-label">Address:</span> ${child.address}`; 

      const medicalDiv = document.createElement('div'); 
      medicalDiv.className = 'child-info';
      medicalDiv.innerHTML = `<span class="child-label">Medical Information:</span> ${child.medicalInformation}`; 

      const removeButton = document.createElement('button');
      removeButton.innerHTML = '<i class="fas fa-times"></i>';
      removeButton.className = 'child-remove';
      removeButton.addEventListener('click', async () => {
        try {
          await deleteChild(child.id); 
          li.remove(); 
          alert('Child deleted successfully.');
        } catch (error) {
          console.error('Failed to remove child', error);
          alert('Failed to remove child. Please try again.');
        }
      });

      li.appendChild(nameDiv);
      li.appendChild(dobDiv);
      li.appendChild(addressDiv);
      li.appendChild(medicalDiv);
      li.appendChild(removeButton);

      container.appendChild(li);
    }
  } catch (error) {
    console.error('Failed to fetch children', error);
    alert('Failed to fetch children. Please try again.');
  }
}

getChildren();
