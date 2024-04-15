const calendar = document.querySelector("#calendar");
const days = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
const months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"];
let currentDate = new Date();

function generateCalendar() {
    // Set month and year
    calendar.innerHTML = `<tr><th colspan='7'>${months[currentDate.getMonth()]} ${currentDate.getFullYear()}</th></tr>`;

    // Add weekday headers
    let weekdayHeaders = "<tr>";
    for (const day of days) {
        weekdayHeaders += `<th>${day}</th>`;
    }
    weekdayHeaders += "</tr>";
    calendar.innerHTML += weekdayHeaders;

    // Add days of month
    let currentDay = 1;
    let firstDay = new Date(currentDate.getFullYear(), currentDate.getMonth(), 1);
    let daysInMonth = new Date(currentDate.getFullYear(), currentDate.getMonth() + 1, 0).getDate();

    while (currentDay <= daysInMonth) {
        let weekRow = "<tr>";
        for (let i = 0; i < 7; i++) {
            if ((currentDay === 1 && i === firstDay.getDay()) || (currentDay > 1 && currentDay <= daysInMonth)) {
                weekRow += `<td>${currentDay}</td>`;
                currentDay++;
            } else {
                weekRow += "<td></td>";
            }
        }
        weekRow += "</tr>"
        calendar.innerHTML += weekRow;
    }
}

generateCalendar();

const todoTable = document.querySelector("#todo-table");

function generateTodoList() {
    const tasks = [
        { task: "Task 1", dueDate: "2024-04-15" },
        { task: "Task 2", dueDate: "2024-04-18" },
        { task: "Task 3", dueDate: "2024-04-20" },
    
    ];

    const tbody = todoTable.querySelector("tbody");
    tbody.innerHTML = "";

    tasks.forEach((task) => {
        const row = document.createElement("tr");
        row.innerHTML = `
            <td>${task.task}</td>
            <td>${task.dueDate}</td>
            <td><button class="delete-button">Delete</button></td>
        `;
        tbody.appendChild(row);
    });
}

generateTodoList();




