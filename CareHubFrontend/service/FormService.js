

// Gets form values from page
export const getValueFromForms = (values) => {
  const body = {};
  for(let i = 0; i < values.length; i++) {
    const value = values[i];
    let formValue = document.querySelector('#' + value);
    if(formValue === null || formValue === undefined) {
      continue;
    }
    formValue = formValue.value;
    body[value] = formValue;
  }
  console.log(body);
  return body;
}

export const createFormWithValues = (container, values) => {
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