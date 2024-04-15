const BASE_URL = 'http://localhost:8080/api/guardian';

const container = document.querySelector('.guardian-container');

const getGuardians = async () => {
    const response = await fetch(BASE_URL, { 
        method: 'GET', 
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    });

    if (!response.ok) {
        throw new Error(`Failed to get guardians: ${response.statusText}`);
    }
  
    const guardians = await response.json();

    for(const guardian of guardians) {
        const li = document.createElement('li');
        li.className = 'guardian-item';

        const nameDiv = document.createElement('div');
        nameDiv.className = 'guardian-info';
        nameDiv.innerHTML = '<span class="guardian-label">Name:</span> ' + guardian.name;

        const addressDiv = document.createElement('div'); 
        addressDiv.className = 'guardian-info';
        addressDiv.innerHTML = '<span class="guardian-label">Address:</span> ' + guardian.address;

        const contactDiv = document.createElement('div');
        contactDiv.className = 'guardian-info';
        contactDiv.innerHTML = '<span class="guardian-label">Phone:</span> ' + guardian.phone;

        li.appendChild(nameDiv);
        li.appendChild(addressDiv);
        li.appendChild(contactDiv);

        container.appendChild(li);
    }
}

getGuardians();