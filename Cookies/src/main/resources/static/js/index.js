// URL base de la API
// Función para manejar el login
async function checkUser() {
    const usernameInput = document.getElementById('username').value;

    try {
        const response = await fetch('/api/name', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: `name=${encodeURIComponent(usernameInput)}`,
        });

        if (response.ok) {
            alert('Login exitoso');
            window.location.href = '/name.html'; // Redirige a la nueva página
        } else {
            alert('Error al iniciar sesión');
        }
    } catch (error) {
        console.error('Error al guardar el nombre:', error);
    }
}


// Función para actualizar el contador
async function updateCounter() {
    try {
        const response = await fetch(`/api/counter`);
        if (response.ok) {
            const count = await response.json();
            document.getElementById('counterValue').textContent = count;
        } else {
            console.error('Error al obtener el contador:', response.status);
        }
    } catch (error) {
        console.error('Error al actualizar el contador:', error);
    }
}

// Actualizar el contador al cargar la página
document.addEventListener('DOMContentLoaded', () => {
    updateCounter();
});
