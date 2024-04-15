import { createFormWithValues, getValueFromForms } from './service/FormService.js';
import { registerVehicle } from './service/VehicleService.js';

const BASE_URL = 'http://localhost:8080/api/vehicle';
const container = document.querySelector('.subform');
const submitButton = document.querySelector(".submit");



const formFields = [
  { name: 'vehicleType', type: 'text', display: 'Vehicle Type: '},
  { name: 'licensePlate', type: 'text', display: 'License Plate:' },
  { name: 'color', type: 'text', display: 'Color:' },
];

createFormWithValues(container, formFields);

submitButton.addEventListener('click', async (e) => {
  e.preventDefault();

  const body = getValueFromForms(formFields.map(field => field.name));
  
  if (body.licensePlate.length !== 7) {
    window.alert('License plate must be exactly 7 characters long.');
    return;
  }

  try {
    const response = await registerVehicle(body);
    console.log(response);
    window.alert(response.message);

    if (response.statusCode === 200) {
      window.location.href = "./RegisterVehicle.html";
    }
  } catch (error) {
    console.error('Error during vehicle registration:', error);
    window.alert('Failed to register vehicle. Please try again.');
  }
});


const logoutButton = document.querySelector('.logout-button');
logoutButton.addEventListener('click', (e) => {
  e.preventDefault();
  console.log('Logout button clicked'); 
  localStorage.removeItem('token'); 
  console.log('Token removed');
  window.location.href = "/login.html"; 
  console.log('Redirecting to login.html');
});
