let currentQuote = null;

window.onload = function() {
    fetchQuote();
    fetchFavorites();
};

function fetchQuote() {
    fetch('/api/random-quote')
    .then(response => response.json())
    .then(quote => {
        currentQuote = quote;
        document.getElementById('quote').textContent = quote.content;
    });
}

function addFavorite() {
    if (currentQuote) {
        fetch('/api/favorite', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(currentQuote)
        })
        .then(() => {
            fetchFavorites();
        });
    }
}

function markAsRead(id) {
    fetch(`/api/favorites/${id}/read`, {
        method: 'POST'
    })
    .then(() => {
        fetchFavorites();
    });
}

function removeFavorite(id) {
    fetch(`/api/favorites/${id}`, {
        method: 'DELETE'
    })
    .then(() => {
        fetchFavorites();
    });
}

function fetchFavorites() {
    fetch('/api/favorites')
    .then(response => response.json())
    .then(favorites => {
        const favoritesList = document.getElementById('favorites');
        favoritesList.innerHTML = '';
        favorites.forEach(quote => {
            const listItem = document.createElement('li');
            listItem.textContent = quote.content;
            if (quote.read) {
                listItem.style.textDecoration = 'line-through';
            }
            const readButton = document.createElement('button');
            readButton.textContent = 'Marquer comme lue';
            readButton.onclick = () => markAsRead(quote.id);
            const removeButton = document.createElement('button');
            removeButton.textContent = 'Supprimer';
            removeButton.onclick = () => removeFavorite(quote.id);
            listItem.appendChild(readButton);
            listItem.appendChild(removeButton);
            favoritesList.appendChild(listItem);
        });
    });
}
