

// Gets form values from page
export const getValueFromForms = (values) => {
  const body = {};
  for(let i = 0; i < values.length; i++) {
    const value = values[i];
    const formValue = document.querySelector('#' + value).value;
    body[value] = formValue;
  }
  console.log(body);
  return body;
}