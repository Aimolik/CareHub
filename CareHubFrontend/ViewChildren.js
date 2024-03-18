
import { getRegisteredChildrenWithGuardianId } from "./service/ChildService.js";
import { getJwtTokenPayload } from './service/AccountService.js';
const container = document.querySelector('.child-container');

const payload = getJwtTokenPayload();
console.log(payload);

const getChildren = async () => {
  const response = await getRegisteredChildrenWithGuardianId(payload.accountId);
  
  for(let i = 0; i < response.length; i++) {
    const child = response[i];
    console.log(child);

    const li = document.createElement('li');
    li.innerText = JSON.stringify(child);
    container.appendChild(li);
  }
}

getChildren();
