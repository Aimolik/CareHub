
import { getValueFromForms } from "./service/FormService.js";
import { login } from "./service/AccountService.js";
const loginButton = document.querySelector('.submit');

loginButton.addEventListener('click', (e) => {
  e.preventDefault();
  const body = getValueFromForms(['email', 'password']);
  body['role'] = "GUARDIAN";
  loginFunction(body);
});

const loginFunction = async (body) => {
  const response = await login(body);
  console.log(response);
  if(response.statusCode === 200) {
    localStorage.setItem('token', response.message);
    window.location.href = "./StaffDashboard.html"
  } else {
    window.alert(response.message);
  }
}