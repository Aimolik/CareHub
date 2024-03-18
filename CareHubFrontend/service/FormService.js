

// Gets form values from page
export const getValueFromForms = (values) => {
  const body = {};
  for(let i = 0; i < values.length; i++) {
    const value = values[i];
    let formValue = document.querySelector('#' + value);
    console.log(formValue)
    if(formValue === null || formValue === undefined) {
      continue;
    }
    formValue = formValue.value;
    body[value] = formValue;
  }
  console.log(body);
  return body;
}