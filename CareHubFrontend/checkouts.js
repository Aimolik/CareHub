const BASE_URL = 'http://localhost:8080/api/child/checkout'; 
const container = document.querySelector('.kid-container');

const getCheckedOutChildren = async () => {
  const response = await fetch(BASE_URL, {
    method: 'GET',
    headers: {
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    }
  });

  if (!response.ok) {
    throw new Error(`Failed to get checked-out children: ${response.statusText}`);
  }

  const children = await response.json();

  for (const child of children) {
    const li = document.createElement('li');
    li.className = 'child-item';

    const nameDiv = document.createElement('div');
    nameDiv.className = 'child-info';
    nameDiv.innerHTML = '<span class="child-label">Name:</span> ' + child.name;

    const statusDiv = document.createElement('div');
    statusDiv.className = 'child-info';
    statusDiv.innerHTML = '<span class="child-label">Status:</span> Checked Out';

    li.appendChild(nameDiv);
    li.appendChild(statusDiv);

    container.appendChild(li);
  }
}

getCheckedOutChildren();

const logoutButton = document.querySelector('.logout-button');
logoutButton.addEventListener('click', (e) => {
  e.preventDefault();
  console.log('Logout button clicked');
  localStorage.removeItem('token');
  console.log('Token removed');
  window.location.href = "/login.html";
  console.log('Redirecting to login.html');
});
