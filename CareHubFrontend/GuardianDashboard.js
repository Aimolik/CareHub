import { getJwtTokenPayload } from "./service/AccountService.js";

const token = localStorage.getItem('token');
console.log(token);
if(token === null) {
  window.alert('Please log in to access this page!');
  window.location.href = "/login.html";
}

// Get JSON payload from JWT token
const payload = getJwtTokenPayload(token);
const email = payload.sub.split('#')[0];

const logoutButton = document.querySelector('.logout-button');
logoutButton.addEventListener('click', (e) => {
  e.preventDefault();
  localStorage.removeItem('token');
  window.location.href = "./login.html";
})
