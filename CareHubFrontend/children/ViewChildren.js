import { getRegisteredChildrenWithGuardianId } from "../service/ChildService.js";
import { getJwtTokenPayload } from '../service/AccountService.js';

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

    const profileImg = document.createElement('img');
    profileImg.src = './img/profile.jpg'; 
    profileImg.className = 'child-profile-img';

    li.appendChild(nameDiv);
    li.appendChild(dobDiv);
    li.appendChild(addressDiv);
    li.appendChild(medicalDiv);

    const removeButton = document.createElement('button');
    removeButton.innerHTML = '<i class="fas fa-times"></i>';
    removeButton.className = 'child-remove';
    li.appendChild(removeButton);

    removeButton.addEventListener('click', async () => {
      try {
          await removeChild(child.id);
          li.remove();  
      } catch (error) {
          console.error('Failed to remove child', error);
      }
    });

    container.appendChild(li);
  }
}

getChildren();

const logoutButton = document.querySelector('.logout-button');
logoutButton.addEventListener('click', (e) => {
  e.preventDefault();
  console.log('Logout button clicked'); 
  localStorage.removeItem('token'); 
  console.log('Token removed');
  window.location.href = "/login.html"; 
  console.log('Redirecting to login.html');
});

export const removeChild = async (childId) => {
  const response = await fetch(`/api/child/${childId}`, {
      method: 'DELETE',
      headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${localStorage.getItem('token')}`
      }
  });

  if (!response.ok) {
      throw new Error(`Failed to remove child: ${response.statusText}`);
  }

  return response.json();
};
