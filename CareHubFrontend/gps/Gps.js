


const GOOGLE_API_KEY = "AIzaSyAwtFaKWoa-I9GwPlrO_7mCLOqfFa2lMCA"

const container = document.querySelector(".container");


const getCoordinates = () => {

  return new Promise((resolve, reject) => {
    navigator.geolocation.getCurrentPosition(position => {
      const lat = position.coords.latitude;
      const long = position.coords.longitude;
      console.log(position);
      resolve({ lat, long })
    }, reject);
  });
};

const createEmbeddedMap = async () => {
  clearContainer();
  const coordinates = await getCoordinates();
  const iframe = document.createElement("iframe");
  iframe.width = 600;
  iframe.height = 450;
  iframe.style= "border: 0";
  iframe.loading = "lazy"
  iframe.allowfullscreen = true;

  const source = `https://www.google.com/maps/embed/v1/directions?`
    + `origin=${coordinates.lat},${coordinates.long}`
    + `&destination=33.730410,-84.425660`
    + `&key=${GOOGLE_API_KEY}`;
  iframe.src = source;

  container.appendChild(iframe);

  console.log(source);
};

const clearContainer = () => {
  while(container.firstChild) {
    container.removeChild(container.firstChild);
  }
}

createEmbeddedMap();

const logoutButton = document.querySelector('.logout-button');
logoutButton.addEventListener('click', (e) => {
  e.preventDefault();
  console.log('Logout button clicked');
  localStorage.removeItem('token');
  console.log('Token removed');
  window.location.href = "/login.html";
  console.log('Redirecting to login.html');
});
