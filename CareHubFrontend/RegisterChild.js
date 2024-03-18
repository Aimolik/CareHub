
import { createFormWithValues, getValueFromForms } from './service/FormService.js';
import { registerChild } from './service/ChildService.js';

const container = document.querySelector('.subform');
const submitButton = document.querySelector(".submit");

const form = [
  { name: 'name', type: 'text' },
  { name: 'dateOfBirth', type: 'date', display: 'Date of birth' },
  { name: 'address', type: 'text' },
  { name: 'medicalInformation', type: 'text', display: 'Medical information' }
];

createFormWithValues(container, form);

submitButton.addEventListener('click', (e) => {
  e.preventDefault();
  const body = getValueFromForms(['name', 'dateOfBirth', 'address', 'medicalInformation']);
  
  register(body);
});

const register = async (body) => {
  const response = await registerChild(body);
  console.log(response);
  window.alert(response.message);
  if(response.statusCode === 200) {
    window.location.href = "./ViewChildren.html";
  }
}