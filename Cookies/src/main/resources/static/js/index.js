let users = [];

async function fetchUsers() {
    try {
        const response = await fetch('/api/users');
        users = await response.json();
    } catch (error) {
        console.error(error);
    }
}

function checkUser() {
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;

    users.forEach(user => {
        if (user.username === username && user.password === password) {
            redirect();
        }
    });
}

function redirect() {
    window.location.href = '/home.html';
}

window.onload = function() {
    fetchUsers();
};