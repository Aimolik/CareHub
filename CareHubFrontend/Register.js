import { registerAccount } from "./service/AccountService.js";
import { getValueFromForms } from "./service/FormService.js";

const submitButton = document.querySelector(".submit");
const container = document.querySelector('.subform');
let accountType = "GUARDIAN";

// Register submission
submitButton.addEventListener('click', (e) => {
  e.preventDefault();
  const body = getValueFromForms(['name', 'email', 'password', 'phone', 'address', 'position', 'contactInfo']);
  register(body);
})

const register = async (account) => {
  const response = await registerAccount(account, accountType);
  console.log(response);
  window.alert(response.message);
  if(response.statusCode === 200) {
    window.location.href = "./Login.html";
  }
};

// Update form based off type of account(staff/guardian)
const guardianForm = [
  { name: 'phone', type: 'tel' },
  { name: 'address', type: 'text' }
]
const staffForm = [
  { name: 'position', type: 'text' },
  { name: 'contactInfo', type: 'text', display: 'Contact info'}
]

document.querySelectorAll('input[name="account-type"]').forEach((elem) => {
  elem.addEventListener('click', () => {
    clearContainer();
    console.log(elem.value);
    if(elem.value === "guardian") {
      addToContainer(guardianForm);
      accountType = "GUARDIAN";
    } else {
      addToContainer(staffForm);
      accountType = "STAFF";
    }
  })
});

const clearContainer = () => {
  while (container.firstChild) {
    container.removeChild(container.firstChild);
  }
}

const addToContainer = (values) => {
  for(let i = 0; i < values.length; i++) {
    const value = values[i];
    const label = document.createElement("label");
    label.setAttribute('for', value.name);
    const display = value.display !== undefined ? value.display : value.name;
    label.textContent = display[0].toUpperCase() + display.substring(1);

    const input = document.createElement("input");
    input.setAttribute('type', value.type);
    input.setAttribute('id', value.name);
    input.setAttribute('name', value.name);
    input.setAttribute('required', '');

    const span = document.createElement("span");
    span.setAttribute('id', value.name + "Error");
    span.setAttribute('class', 'error');

    const br = document.createElement("br");

    container.appendChild(label);
    container.appendChild(input);
    container.appendChild(span);
    container.appendChild(br);
  }
}

addToContainer(guardianForm);