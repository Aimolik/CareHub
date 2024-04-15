
const BASE_URL = 'http://localhost:8080/api/child'; 
const container = document.querySelector('.child-container');

const getChildren = async () => {
  const response = await fetch(BASE_URL, { 
      method: 'GET',
      headers: {
          'Authorization': 'Bearer ' + localStorage.getItem('token')
      }
  });

  if (!response.ok) {
      throw new Error(`Failed to get children: ${response.statusText}`);
  }
  
  const children = await response.json();

  for(const child of children) {
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

const logoutButton = document.querySelector('.logout-button');
logoutButton.addEventListener('click', (e) => {
  e.preventDefault();
  console.log('Logout button clicked'); 
  localStorage.removeItem('token'); 
  console.log('Token removed');
  window.location.href = "/login.html"; 
  console.log('Redirecting to login.html');
});