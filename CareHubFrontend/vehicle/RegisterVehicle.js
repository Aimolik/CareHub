const BASE_URL = 'http://20.55.39.9:8080';
import { createFormWithValues, getValueFromForms } from '../service/FormService.js';
import { registerGuardianVehicle } from '../service/VehicleService.js';

const container = document.querySelector('.subform');
const submitButton = document.querySelector(".submit");

const form = [
    { name: 'vehicleType', type: 'text' },
    { name: 'licensePlate', type: 'text', display: 'License Plate' },
    { name: 'color', type: 'text' }
];

createFormWithValues(container, form);

submitButton.addEventListener('click', async (e) => {
    e.preventDefault();
    const body = getValueFromForms(['vehicleType', 'licensePlate', 'color']);

    try {
        const guardianId = await getGuardianId();
        const response = await registerGuardianVehicle(body, guardianId);
        console.log(response);
        window.alert(response.message);
        if(response.statusCode === 200) {
            window.location.href = "./RegisterVehicle.html";
        }
    } catch (error) {
        console.error('Error during vehicle registration:', error);
        window.alert('Failed to register vehicle. Please try again.');
    }
});

const getGuardianId = async () => {
    const token = localStorage.getItem('token');
    try {
        const response = await fetch(BASE_URL + '/api/guardian/currentGuardianId', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + token
            }
        });
        if (!response.ok) {
            throw new Error('Failed to fetch guardian ID');
        }
        const data = await response.json();
        return data.guardianId;
    } catch (error) {
        console.error('Error fetching guardian ID:', error);
        throw error; 
    }
};
