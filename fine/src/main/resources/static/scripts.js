document.addEventListener('DOMContentLoaded', function() {
    const searchForm = document.getElementById('searchForm');
    const playerSearchForm = document.getElementById('playerSearchForm');

    if (searchForm) {
        searchForm.addEventListener('submit', function(event) {
            event.preventDefault();
            searchClans();
        });
    }

    if (playerSearchForm) {
        playerSearchForm.addEventListener('submit', function(event) {
            event.preventDefault();
            searchPlayers();
        });
    }
});

function searchClans() {
    const searchInput = document.getElementById('searchInput').value;

    fetch(`http://localhost:8080/api/clans?search=${encodeURIComponent(searchInput)}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            displayClans(data);
        })
        .catch(error => {
            console.error('Error loading clan statistics:', error);
        });
}

function displayClans(clans) {
    const clanStatsTable = document.getElementById('clanStatsTable').getElementsByTagName('tbody')[0];
    clanStatsTable.innerHTML = '';

    if (clans.length === 0) {
        clanStatsTable.innerHTML = '<tr><td colspan="7">No clans found.</td></tr>';
        return;
    }

    clans.forEach(clan => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${clan.clanName}</td>
            <td>${clan.averageBattles}</td>
            <td>${clan.averageDamage}</td>
            <td>${clan.averageExp}</td>
            <td>${clan.winRate}</td>
            <td>${clan.clanRating}</td>
            <td>${clan.totalMembers}</td>
        `;
        clanStatsTable.appendChild(row);
    });
}

function searchPlayers() {
    const playerSearchInput = document.getElementById('playerSearchInput').value;

    fetch(`http://localhost:8080/api/players?search=${encodeURIComponent(playerSearchInput)}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            displayPlayers(data);
        })
        .catch(error => {
            console.error('Error loading player statistics:', error);
        });
}

function displayPlayers(players) {
    const playerStatsTable = document.getElementById('playerStatsTable').getElementsByTagName('tbody')[0];
    playerStatsTable.innerHTML = '';

    if (players.length === 0) {
        playerStatsTable.innerHTML = '<tr><td colspan="6">No players found.</td></tr>';
        return;
    }

    players.forEach(player => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${player.nickname}</td>
            <td>${player.totalBattles}</td>
            <td>${player.winRate}</td>
            <td>${player.averageDamage}</td>
            <td>${player.averageExp}</td>
            <td>${player.clanName ? player.clanName : 'No Clan'}</td>
        `;
        playerStatsTable.appendChild(row);
    });
}
