import { registerAccount } from "../service/AccountService.js";
import { getValueFromForms, createFormWithValues } from "../service/FormService.js";

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
      createFormWithValues(container, guardianForm);
      accountType = "GUARDIAN";
    } else {
      createFormWithValues(container, staffForm)
      accountType = "STAFF";
    }
  })
});

const clearContainer = () => {
  while (container.firstChild) {
    container.removeChild(container.firstChild);
  }
}

createFormWithValues(container, guardianForm);