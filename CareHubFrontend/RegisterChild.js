import { createFormWithValues, getValueFromForms } from '../service/FormService.js';
import { registerChild } from '../service/ChildService.js';

const container = document.querySelector('.subform');
const submitButton = document.querySelector(".submit");

const form = [
  { name: 'name', type: 'text' },
  { name: 'dateOfBirth', type: 'date', display: 'Date of birth' },
  { name: 'address', type: 'text' },
  { name: 'medicalInformation', type: 'text', display: 'Medical information' }
];

createFormWithValues(container, form);

const register = async (body) => {
  const response = await registerChild(body);
  console.log(response);
  window.alert(response.message);
  if(response.statusCode === 200) {
    window.location.href = "./ViewChildren.html";
  }
}

function calculateAge(dob) {
  let today = new Date();
  let birthDate = new Date(dob);
  let age = today.getFullYear() - birthDate.getFullYear();
  let m = today.getMonth() - birthDate.getMonth();
  if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
    age--;
  }
  return age;
}

submitButton.addEventListener('click', (e) => {
  e.preventDefault();
  const body = getValueFromForms(['name', 'dateOfBirth', 'address', 'medicalInformation']);

  const dob = body.dateOfBirth;

  const age = calculateAge(dob);

  if (age < 2 || age > 5) {
    alert("Child should be between 2 and 5 years old.")
  } else {
    register(body);
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