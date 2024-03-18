import { registerAccount } from "./service/AccountService.js";
import { getValueFromForms } from "./service/FormService.js";

const submitButton = document.querySelector(".submit");


submitButton.addEventListener('click', (e) => {
  e.preventDefault();
  const body = getValueFromForms(['name', 'email', 'password', 'phone', 'address']);
  register(body);
})

const register = async (account) => {
  const response = await registerAccount(account, "GUARDIAN");
  console.log(response);
  window.alert(response.message);
  if(response.statusCode === 200) {
    window.location.href = "./Login.html";
  }
};
