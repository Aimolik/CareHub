
import { getValueFromForms } from "../service/FormService.js";
import { login, getJwtTokenPayload } from "../service/AccountService.js";
const loginButton = document.querySelector('.submit');
let accountType = "GUARDIAN";

loginButton.addEventListener('click', (e) => {
  e.preventDefault();
  const body = getValueFromForms(['email', 'password']);
  body['role'] = accountType;

  loginFunction(body);
});

const loginFunction = async (body) => {
  const response = await login(body);
  console.log(response);
  if(response.statusCode === 200) {
    localStorage.setItem('token', response.message);
    const payload = getJwtTokenPayload();
    const role = payload.sub.split('#')[1];
    if(role === "GUARDIAN") {
      window.location.href = "../dashboard/GuardianDashboard.html";
    } else {
      window.location.href = "../dashboard/StaffDashboard.html";
    }
  } else {
    window.alert(response.message);
  }
}

document.querySelectorAll('input[name="account-type"]').forEach((elem) => {
  elem.addEventListener('click', () => {
    console.log(elem.value);
    if(elem.value === "guardian") {
      accountType = "GUARDIAN";
    } else {
      accountType = "STAFF";
    }
  })
});