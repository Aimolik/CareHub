import { getRegisteredChildrenWithGuardianId } from "./service/ChildService.js";
import { getJwtTokenPayload } from './service/AccountService.js';
const BASE_URL = 'http://20.55.39.9:8080';
const container = document.querySelector('.child-container');

const getChildren = async () => {
  const payload = getJwtTokenPayload();
  const children = await getRegisteredChildrenWithGuardianId(payload.accountId);

  for (let i = 0; i < children.length; i++) {
    const child = children[i];

    const li = document.createElement('li');
    li.className = 'child-item';

    const nameDiv = document.createElement('div');
    nameDiv.className = 'child-info';
    nameDiv.innerHTML = '<span class="child-label">Name:</span> ' + child.name;

    const scheduleDiv = document.createElement('div');
    scheduleDiv.className = 'child-info';

    const schedule = await getChildSchedule(child.id);
    scheduleDiv.innerHTML = '<span class="child-label">Schedule:</span> ' + schedule;

    li.appendChild(nameDiv);
    li.appendChild(scheduleDiv);

    container.appendChild(li);
  }
}

const getChildSchedule = async (childId) => {
    return `
      <p>7:00am - 8:00 am: Playtime</p>
      <p>8:00 - 10:00am: Learning</p>
      <p>11:00am - 12:30pm: Lunch and Preparation for Nap</p>
      <p>12:30pm-3:00pm: Nap/Quiet Time</p>
      <p>3:00-4:00pm: Snack Time and play</p>
      <p>4:00-5:30pm: Free Play & Helping Out</p>
    `;
  }
  
getChildren();

